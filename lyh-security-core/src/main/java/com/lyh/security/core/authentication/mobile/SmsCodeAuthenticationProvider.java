package com.lyh.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/6 23:31
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

  private UserDetailsService userDetailsService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken)authentication;
    UserDetails user = userDetailsService.loadUserByUsername((String) authentication.getPrincipal());

    if(user == null){
      throw new InternalAuthenticationServiceException("无法获取用户信息");
    }
    SmsCodeAuthenticationToken authenticationTokenResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());

    // 不是太明白
    authenticationTokenResult.setDetails(authenticationToken.getDetails());
    return authenticationTokenResult;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
  }

  public UserDetailsService getUserDetailsService() {
    return userDetailsService;
  }

  public void setUserDetailsService(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }
}
