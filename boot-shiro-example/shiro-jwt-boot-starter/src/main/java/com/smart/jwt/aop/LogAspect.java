package com.smart.jwt.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
 
    @Autowired
    LogService logService;
 
    @Autowired
    SysUserService userService;
 
    @Pointcut("execution(* com.*.controller..*(..)))")
    public void logPointCut() {
    }
 
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) {
        //判断token信息
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String token = request.getHeader("token");
        //如果传的值不存在：1：登录 2：过期
        String uri = request.getRequestURI();
        String method = request.getMethod();
        boolean isLogin = StringUtils.equals("/login", uri) && StringUtils.equals("POST", method);
        JWTentity user = null;
        R r =R.error(Constant.jwtErrorCode, "session失效，请重新登录");
        if (StringUtils.isEmpty(token)) {
            //进入的是登录界面
            if (!isLogin) {
                //其他接口：session过期
                return r;
            }
        } else if (!isLogin) {
            //校验
            user = JWTUtils.unsign(token, JWTentity.class);
            if (user == null) {
                //返回错误信息
                return r;
            } else {
                Long userId = user.getUserId();
                //判断是否在黑名单里面
                List tokens = userService.getUserToken(userId);
                if (tokens.contains(token)) {
                    return r;
                }
                // 执行时长(毫秒)
                long time = Constant.jwtMaxAge - user.getLoginDate().getSeconds();
                if (0 < time && time <= Constant.jwtDelayMinAge) {
                    return r;
                }
                //延时
                delayTime(token, user, userId, time);
            }
        }
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = null;
        try {
            result = point.proceed();
        } catch (Throwable throwable) {
           logger.error(throwable.toString());
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //异步保存日志
        saveLog(point, time, user);
        return result;
    }
    private void delayTime(String token, JWTentity user, Long userId, long time) {
        if (time > 0 && time < Constant.jwtDelayMaxAge) { //延时
            HttpServletResponse response = HttpContextUtils.getHttpServletResponse();
            user.setLoginDate(new Date());
            String jwtToken = JWTUtils.sign(user, Constant.jwtMaxAge);
            response.setHeader("token", jwtToken);
            //保存黑名单
            SysUserTokenEntity userToken = new SysUserTokenEntity();
            userToken.setUserId(userId);
            userToken.setTokenId(token);
            userToken.setOperDate(new Date());
            userService.saveUserTokenEntity(userToken);
        }
    }
    void saveLog(ProceedingJoinPoint joinPoint, long time, JWTentity user) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogEntity sysLog = new LogEntity();
        //Log syslog = method.getAnnotation(Log.class);
        //if (syslog != null) {
            // 注解上的描述
           // sysLog.setOperation(className + "." + methodName + "()");
       // }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的参数
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        Object[] args = joinPoint.getArgs();
//        String params1 = JSONUtils.beanToJson(args[0]);
  //      sysLog.setParams(params1);
        // 获取request
        // HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        StringBuffer url = request.getRequestURL();
        String uri = request.getRequestURI();
        // 设置IP地址
        String ip = IPUtils.getIpAddr(request);
        sysLog.setIp(ip);
        if (user != null) {
            Long userId = user.getUserId();
            if (userId != null) {
                sysLog.setUserId(userId);
            }
            String userName = user.getUserName();
            if (StringUtils.isNotEmpty(userName)) {
                sysLog.setUsername(userName);
            }
        }
        sysLog.setTime((int) time);
        // 系统当前时间
        Date date = new Date();
        sysLog.setGmtCreate(date);
        // 保存系统日志
        logService.save(sysLog);
    }
