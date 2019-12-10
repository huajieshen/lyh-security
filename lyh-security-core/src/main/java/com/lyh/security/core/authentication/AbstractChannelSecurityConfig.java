package com.lyh.security.core.authentication;

import com.lyh.security.core.constant.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/7 12:29
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  protected AuthenticationSuccessHandler customAuthenticationSuccessHandler;

  @Autowired
  protected AuthenticationFailureHandler customAuthenticationFailureHandler;

  protected void applyPasswordAuthenticationConfig(HttpSecurity http)throws Exception{
    http.formLogin()
            .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)                 //  "/authentication/require"
            .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)   //  "/authentication/form"
            .successHandler(customAuthenticationSuccessHandler)
            .failureHandler(customAuthenticationFailureHandler);
  }

}
