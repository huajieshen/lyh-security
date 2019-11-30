package com.lyh.service.impl;

import com.lyh.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/21 1:07
 */
@Service
public class HelloServiceImpl implements HelloService {
  @Override
  public String greeting(String name) {
    System.out.println("greeting");
    return "hello " + name;
  }
}
