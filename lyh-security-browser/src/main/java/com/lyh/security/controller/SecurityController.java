package com.lyh.security.controller;

import com.lyh.security.core.properties.SecurityProperties;
import com.lyh.security.support.SimpleResponse;
import com.lyh.security.support.SocialUserInfo;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/29 13:52
 */

@RestController
public class SecurityController {

  private Logger logger = LoggerFactory.getLogger(getClass());

  // 请求缓存  （HttpSessionRequestCache 当前的请求缓存的session里面去）
  // 其中HttpSessionRequestCache为Spring Security提供的用于缓存请求的对象，通过调用它的getRequest方法可以获取到本次请求的HTTP信息
  private RequestCache requestCache = new HttpSessionRequestCache();

  // DefaultRedirectStrategy的sendRedirect为Spring Security提供的用于处理重定向的方法
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  @Autowired
  private SecurityProperties securityProperties;

  @Autowired
  private ProviderSignInUtils providerSignInUtils;

  /**
   * 当需要身份认证时，跳转到这里
   * @param request
   * @param response
   * @return
   */
  @RequestMapping("/authentication/require")
  @ResponseStatus(code = HttpStatus.UNAUTHORIZED)  // 未授权的状态码
  public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response)throws IOException {

    // 之前缓存的请求
    SavedRequest savedRequest = requestCache.getRequest(request, response);

    if(savedRequest != null){
      String targetUrl = savedRequest.getRedirectUrl();
      logger.info("引发跳转的请求是： " + targetUrl);
      if(StringUtils.endsWithIgnoreCase(targetUrl, ".html")){
        redirectStrategy.sendRedirect(request, response, securityProperties.getBrowserProperties().getLoginPage());
      }
    }

    return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页");
  }

  @GetMapping("/social/user")
  public SocialUserInfo getSocialUserInfo(HttpServletRequest request){
    SocialUserInfo userInfo = new SocialUserInfo();
    Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
    userInfo.setProviderId(connection.getKey().getProviderId());
    userInfo.setProviderUserId(connection.getKey().getProviderUserId());
    userInfo.setNickname(connection.getDisplayName());
    userInfo.setHeadimg(connection.getImageUrl());
    return userInfo;
  }

  @GetMapping("/session/invalid")
  @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
  public SimpleResponse sessionInvalid(){
    String message = "session失效";
    return new SimpleResponse(message);
  }

}
