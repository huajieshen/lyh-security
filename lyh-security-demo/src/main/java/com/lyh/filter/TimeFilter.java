package com.lyh.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/26 21:56
 */
//@Component
public class TimeFilter implements Filter {
//过滤器可以获取原始http请求和响应的信息，获取不到真正处理请求的方法信息

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("time filter init");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    System.out.println("time filter start");
    Long start = new Date().getTime();
    filterChain.doFilter(request, response);
    System.out.println("time filter 耗时: " + (new Date().getTime()-start));

    System.out.println("time filter finish");

  }

  @Override
  public void destroy() {
    System.out.println("time filter destroy");
  }
}
