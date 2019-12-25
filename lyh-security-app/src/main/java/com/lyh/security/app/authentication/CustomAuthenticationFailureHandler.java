package com.lyh.security.app.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyh.security.core.properties.SecurityProperties;
import com.lyh.security.core.support.SimpleResponse;
import com.lyh.security.core.enums.LoginResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/2 20:57
 */
@Component("customAuthenticationFailureHandler")
//public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private SecurityProperties securityProperties;

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    logger.info("登录失败");

    if (LoginResponseType.JSON.equals(securityProperties.getBrowserProperties().getLoginResponseType())) {

      response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));

    } else {

      super.onAuthenticationFailure(request, response, exception);

    }


  }
}
