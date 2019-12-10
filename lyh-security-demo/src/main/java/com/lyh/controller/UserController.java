package com.lyh.controller;


import com.lyh.dto.User;
import com.lyh.dto.UserQueryCondition;
import com.lyh.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/20 16:38
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @GetMapping("/me")
//  public Object getCurrentUser(){
//    return SecurityContextHolder.getContext().getAuthentication();
//  }
  public Object getCurrentUser(@AuthenticationPrincipal UserDetails user){
    return SecurityContextHolder.getContext().getAuthentication();
  }


  // @Valid 校验
  @PostMapping
  public User create(@Valid @RequestBody User user, BindingResult errors) {

    if (errors.hasErrors()) {
      errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
    }
    System.out.println(user.getId());
    System.out.println(user.getUsername());
    System.out.println(user.getPassword());
    System.out.println(user.getBirthday());

    user.setId(1);
    return user;
  }

  @PutMapping("/{id:\\d+}")
  public User update(@Valid @RequestBody User user, BindingResult errors) {

    if (errors.hasErrors()) {
      errors.getAllErrors().stream().forEach(error -> {
//        FieldError fieldError = (FieldError)error;
//        String message = fieldError.getField() + " " + error.getDefaultMessage();
//        System.out.println(message);
        System.out.println(error.getDefaultMessage());
      });
    }
    System.out.println(user.getId());
    System.out.println(user.getUsername());
    System.out.println(user.getPassword());
    System.out.println(user.getBirthday());

    user.setId(1);
    return user;
  }

  @DeleteMapping("/{id:\\d+}")
  public void delete(@PathVariable String id){
    System.out.println(id);
  }



  @GetMapping
//  @ApiOperation(value = "用户查询服务")
//  public List<User> query(@RequestParam(required = false, defaultValue = "username") String username){
  public List<User> query(UserQueryCondition condition, @PageableDefault(page = 2, size = 17, sort = "username, asc") Pageable pageable) {

    System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));

    System.out.println(pageable.getPageSize());
    System.out.println(pageable.getPageNumber());
    System.out.println(pageable.getSort());

    List<User> users = new ArrayList<>();
    users.add(new User());
    users.add(new User());
    users.add(new User());
    return users;
  }

  //参数只能接收数字，正则表达式
  @GetMapping("/{id:\\d+}")

//  public User getInfo(@ApiParam(value = "用户id") @PathVariable Integer id) {
  public User getInfo(@PathVariable Integer id) {

    System.out.println("进入getInfo服务");

//    throw new UserNotExistException(id);
//    System.out.println(id);
    User user = new User();
    user.setUsername("tom");
    return user;
  }


}
