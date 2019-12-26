package com.lyh.security.app.social.impl;

import com.lyh.security.core.social.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author shenhj
 * @version 1.0
 * Description: 设置app下springsocial走的成功处理器
 * @date 2019/12/25 23:50
 */
@Component
public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor {
  //认证成功后返回token的成功处理器
  @Autowired
  private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

  @Override
  public void process(SocialAuthenticationFilter socialAuthenticationFilter) {

    socialAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
  }
}
