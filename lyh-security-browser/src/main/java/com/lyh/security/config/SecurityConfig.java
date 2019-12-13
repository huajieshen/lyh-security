package com.lyh.security.config;

import com.lyh.security.core.authentication.AbstractChannelSecurityConfig;
import com.lyh.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.lyh.security.core.constant.SecurityConstants;

import com.lyh.security.core.validate.ValidateCodeSecurityConfig;
import com.lyh.security.core.properties.SecurityProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/28 19:15
 */
@Configuration
//@EnableWebSecurity
public class SecurityConfig extends AbstractChannelSecurityConfig {

  @Autowired
  private SecurityProperties securityProperties;

  @Autowired
  private UserDetailsService customUserDetailsService;

  @Autowired
  private DataSource dataSource;

  @Autowired
  private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

  @Autowired
  private ValidateCodeSecurityConfig validateCodeSecurityConfig;

  @Autowired
  private SpringSocialConfigurer lyhSocialSecurityConfig;

  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
    tokenRepository.setDataSource(dataSource);
//    tokenRepository.setCreateTableOnStartup(true);
    return tokenRepository;
  }

  // 处理密码加密解密
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }



  @Override
  protected void configure(HttpSecurity http) throws Exception {

    applyPasswordAuthenticationConfig(http);

    http.apply(validateCodeSecurityConfig)
            .and()
            .apply(smsCodeAuthenticationSecurityConfig)
            .and()
            .apply(lyhSocialSecurityConfig)
            .and()
            .rememberMe()
            .tokenRepository(persistentTokenRepository())
            .tokenValiditySeconds(securityProperties.getBrowserProperties().getRememberMeSeconds())
            .userDetailsService(customUserDetailsService)
            .and()

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
                    "/user/regist")
            .permitAll()
            .anyRequest()
            .authenticated() //
            .and()
            .csrf().disable(); //关闭csrf防护

  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    // 使用密码加密
//    auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
  }
}
