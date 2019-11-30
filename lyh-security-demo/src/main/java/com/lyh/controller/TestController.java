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

    @GetMapping("/hello")
    public String hello(){
        return "hello spring security";
    }
}
