package com.lyh.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/26 23:16
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {
//拦截器可以获取原始http请求和响应的信息，也可以获取到真正处理请求的方法信息，但是获取不到那个方法被调用的时候真正的参数值

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    System.out.println("preHandle");
    request.setAttribute("startTime", new Date().getTime());
    System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
    System.out.println(((HandlerMethod)handler).getMethod().getName());
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    System.out.println("postHandle");
    Long start = (Long)request.getAttribute("startTime");
    System.out.println("time interceptor 耗时: " + (new Date().getTime()-start));

  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    System.out.println("afterCompletion");
    Long start = (Long)request.getAttribute("startTime");
    System.out.println("time interceptor 耗时: " + (new Date().getTime()-start));
    System.out.println("ex is " + ex);

  }
}
