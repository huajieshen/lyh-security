package com.lyh.security.app.config;


import com.lyh.security.app.social.openid.OpenIdAuthenticationSecurityConfig;
import com.lyh.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.lyh.security.core.constant.SecurityConstants;
import com.lyh.security.core.validate.ValidateCodeSecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

import com.lyh.security.core.properties.SecurityProperties;

/**
 * @author shenhj
 * @date 2019/12/20 20:28
 * @version 1.0
 * Description: 资源服务器
 */
@Configuration
@EnableResourceServer
public class LyhResourcesServerConfig extends ResourceServerConfigurerAdapter {

  @Autowired
  private SecurityProperties securityProperties;

  @Autowired
  protected AuthenticationSuccessHandler customAuthenticationSuccessHandler;

  @Autowired
  protected AuthenticationFailureHandler customAuthenticationFailureHandler;

  @Autowired
  private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

  @Autowired
  private ValidateCodeSecurityConfig validateCodeSecurityConfig;

  @Autowired
  private SpringSocialConfigurer lyhSocialSecurityConfig;

  //openId校验配置类
  @Autowired
  private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;


  @Override
  public void configure(HttpSecurity http) throws Exception {

    http.formLogin()
            .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)                 //  "/authentication/require"
            .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)   //  "/authentication/form"
            .successHandler(customAuthenticationSuccessHandler)
            .failureHandler(customAuthenticationFailureHandler);


    http//.apply(validateCodeSecurityConfig)
          //  .and()
            //短信校验相关配置
            .apply(smsCodeAuthenticationSecurityConfig)
            .and()
            //springsocial校验相关配置
            .apply(lyhSocialSecurityConfig)
            .and()
//            .apply(openIdAuthenticationSecurityConfig)
//            .and()
            .authorizeRequests()//下边的都是授权的配置
            .antMatchers(
                    SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,  //  "/authentication/require",
                    securityProperties.getBrowserProperties().getLoginPage(),
                    SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,   // "/authentication/form"
                    SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,  // "/authentication/mobile"

                    SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                    securityProperties.getBrowserProperties().getSignUpUrl(),



//                    "/authentication/require",
//                    securityProperties.getBrowserProperties().getLoginPage(),
//                    "/code/*",
                    "/error",
                    "/user/regist", "/session/invalid")
            .permitAll()
            .anyRequest()
            .authenticated() //
            .and()
            .csrf().disable(); //关闭csrf防护
  }
}
