package com.lyh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/10/17 14:53
 */
@RestController
public class TestController {


//    redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fauth%2Fqq&state=bc9e70a0-c431-441e-94dd-10bb6d93f4f1

    @GetMapping("/hello")
    public String hello(){
        return "hello spring security";
    }
}
