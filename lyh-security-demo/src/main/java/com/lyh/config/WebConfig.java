package com.lyh.config;

import com.lyh.filter.TimeFilter;
import com.lyh.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/26 22:47
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Autowired
  TimeInterceptor timeInterceptor;

  //异步拦截器注册
//  @Override
//  public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
////    configurer.registerCallableInterceptors(interceptors);
////    configurer.registerDeferredResultInterceptors()
//
//  }

  //  同步拦截器注册
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
//    registry.addInterceptor(timeInterceptor);
  }

//  @Bean
  public FilterRegistrationBean timeFilter(){
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    TimeFilter timeFilter = new TimeFilter();
    registrationBean.setFilter(timeFilter);

    List<String> urls = new ArrayList<>();
    urls.add("/*");
    registrationBean.setUrlPatterns(urls);

    return registrationBean;
  }
}
