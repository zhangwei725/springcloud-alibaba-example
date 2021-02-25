package com.smart.jwt.intercept;

import com.smart.jwt.config.JwtConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ShiroJwtInterceptor implements HandlerInterceptor {
    private static final String PREFIX_BEARER = "Bearer ";

    @Autowired
    private JwtConfig jwtConfig;


    /**
     * 预处理回调方法，实现处理器的预处理（如检查登陆），第三个参数为响应的处理器，自定义 Controller
     * 返回值：
     * true 表示继续流程（如调用下一个拦截器或处理器）；
     * false 表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过 response 来产生响应。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间，还可以进行一些资源清理，类似于 try-catch-finally 中的 finally
     * 但仅调用处理器执行链中 preHandle 返回 true 的拦截器的 afterCompletion。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}