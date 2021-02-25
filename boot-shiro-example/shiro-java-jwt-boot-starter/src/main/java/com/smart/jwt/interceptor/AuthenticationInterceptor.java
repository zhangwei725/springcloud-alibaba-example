package com.smart.jwt.interceptor;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    /**
     * 请求到达控制器之前
     * @param request
     * @param response
     * @param handler
     * @return true 表示放行
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }

    /**
     * 整个请求完成之后
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
