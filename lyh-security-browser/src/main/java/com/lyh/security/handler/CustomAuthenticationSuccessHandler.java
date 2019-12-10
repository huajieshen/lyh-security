package com.lyh.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyh.security.core.enums.LoginResponseType;
import com.lyh.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/2 20:10
 */
@Component("customAuthenticationSuccessHandler")
//public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private SecurityProperties securityProperties;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

    logger.info("登录成功");

    if (LoginResponseType.JSON.equals(securityProperties.getBrowserProperties().getLoginResponseType())) {
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write(objectMapper.writeValueAsString(authentication));
    } else {
      super.onAuthenticationSuccess(request, response, authentication);
    }


  }
}