package com.lyh.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/27 19:34
 */
//@Aspect
//@Component
public class TimeAspect {
//  可以获取到那个方法真正被调用的参数值，但是获取不到原始的http请求和响应的对象

  @Around("execution(* com.lyh.controller.UserController.*(..))")
  public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable{

    System.out.println("time aspect start");

    Object[] args = pjp.getArgs();
    for (Object arg: args){
      System.out.println("arg is  " + arg);
    }

    long start = new Date().getTime();

    Object object = pjp.proceed();

    System.out.println("time aspect 耗时:" + (new Date().getTime() - start));

    System.out.println("time aspect end");
    return object;
  }

}
