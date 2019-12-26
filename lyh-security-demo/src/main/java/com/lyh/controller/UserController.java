package com.lyh.controller;


import com.lyh.dto.User;
import com.lyh.dto.UserQueryCondition;
import com.lyh.exception.UserNotExistException;
import com.lyh.security.app.social.AppSignUpUtils;
import com.lyh.security.core.properties.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
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

  @Autowired
  private ProviderSignInUtils providerSignInUtils;

  @Autowired
  private AppSignUpUtils appSignUpUtils;

  @Autowired
  private SecurityProperties securityProperties;

  @PostMapping("/regist")
  public void regist(User user, HttpServletRequest request) {

    //不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
    String userId = user.getUsername();
//    providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
    appSignUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
  }


  @GetMapping("/me")
//  public Object getCurrentUser(){
//    return SecurityContextHolder.getContext().getAuthentication();
//  }
  public Object getCurrentUser(@AuthenticationPrincipal UserDetails user){
    return SecurityContextHolder.getContext().getAuthentication();
  }

  @GetMapping("/me2")
  public Object getCurrentUser2(Authentication authentication, HttpServletRequest request) throws UnsupportedEncodingException {
    //方式2---方式1的简写版
    //从请求头中获取到JWT
//    String token = StringUtils.substringAfter(request.getHeader("Authorization"), "bearer ");

    String header = request.getHeader("Authorization");
    String token = StringUtils.substringAfter(header, "bearer ");

    //对JWT进行解析,注意由于JWT生成时编码格式用的UTF-8（看源码可以追踪到）
    //但Jwts工具用到的默认编码格式不是，所以要设置其编码格式为UTF-8
    Claims claims = Jwts.parser()
            .setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
            .parseClaimsJws(token).getBody();
    //取出扩展信息，并打印
    String company = (String) claims.get("company");

    System.err.println("----->" + company);
    return authentication;
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
