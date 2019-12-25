package com.lyh.security.app.social.openid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author shenhj
 * @version 1.0
 * Description:
 * @date 2019/12/24 18:13
 */
@Component
public class OpenIdAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  @Autowired
  private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

  @Autowired
  private AuthenticationFailureHandler customAuthenticationFailureHandler;

  @Autowired
  private SocialUserDetailsService customUserDetailsService;

  @Autowired
  private UsersConnectionRepository lyhJdbcUsersConnectionRepository;

  @Override
  public void configure(HttpSecurity http) throws Exception {

    OpenIdAuthenticationFilter OpenIdAuthenticationFilter = new OpenIdAuthenticationFilter();
    OpenIdAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
    OpenIdAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
    OpenIdAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);

    OpenIdAuthenticationProvider OpenIdAuthenticationProvider = new OpenIdAuthenticationProvider();
    OpenIdAuthenticationProvider.setUserDetailsService(customUserDetailsService);
    OpenIdAuthenticationProvider.setUsersConnectionRepository(lyhJdbcUsersConnectionRepository);

    http.authenticationProvider(OpenIdAuthenticationProvider)
            .addFilterAfter(OpenIdAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

  }

}
