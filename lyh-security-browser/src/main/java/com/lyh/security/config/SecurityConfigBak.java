//package com.lyh.security.config;
//
//import com.lyh.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
//import com.lyh.security.core.properties.SecurityProperties;
//import com.lyh.security.core.validate.SmsCodeFilter;
//import com.lyh.security.core.validate.ValidateCodeFilter;
//import com.lyh.security.handler.CustomAuthenticationFailureHandler;
//import com.lyh.security.handler.CustomAuthenticationSuccessHandler;
//import com.lyh.security.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//
//import javax.sql.DataSource;
//
///**
// * @author shenhj
// * @version 1.0
// * @date 2019/11/28 19:15
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfigBak extends WebSecurityConfigurerAdapter {
//
//  @Autowired
//  private CustomUserDetailsService customUserDetailsService;
//
//  @Autowired
//  private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
//
//
//  @Autowired
//  private DataSource dataSource;
//
//  @Bean
//  public PersistentTokenRepository persistentTokenRepository() {
//    JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//    tokenRepository.setDataSource(dataSource);
////    tokenRepository.setCreateTableOnStartup(true);
//    return tokenRepository;
//  }
//
//  // 处理密码加密解密
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//  @Autowired
////  private AuthenticationSuccessHandler customAuthenticationSuccessHandler;
//  private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
//
//  @Autowired
////  private AuthenticationFailureHandler customAuthenticationFailureHandler;
//  private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
//
//
//  @Autowired
//  private SecurityProperties securityProperties;
//
////  @Autowired
////  private ValidateCodeFilter validateCodeFilter;
//
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//
//    //验证码过滤器
//    ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
//    //验证码过滤器中使用自己的错误处理
////    validateCodeFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
////    validateCodeFilter.setSecurityProperties(securityProperties);
//    validateCodeFilter.afterPropertiesSet();
//
//
//    //验证码过滤器
//    SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
//    //验证码过滤器中使用自己的错误处理
////    validateCodeFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
////    validateCodeFilter.setSecurityProperties(securityProperties);
//    validateCodeFilter.afterPropertiesSet();
//
//
//    //把验证码过滤器加载登录过滤器前边
//    http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
//            .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
//            .formLogin()
//            //表单认证
//
////            .loginPage("/login-signIn.html")
//            .loginPage("/authentication/require") //处理用户认证SecurityController
//            //登录过滤器UsernamePasswordAuthenticationFilter默认登录的url是"/login"，在这能改
//            .loginProcessingUrl("/authentication/form")
//            .successHandler(customAuthenticationSuccessHandler)//自定义的认证后处理器
//            .failureHandler(customAuthenticationFailureHandler)//登录失败后的处理
//            .and()
//
//            .rememberMe()
//            .tokenRepository(persistentTokenRepository())
//            .tokenValiditySeconds(securityProperties.getBrowserProperties().getRememberMeSeconds())
//            .userDetailsService(customUserDetailsService)
//            .and()
//
//            .authorizeRequests()//下边的都是授权的配置
////            .antMatchers("/login-signIn.html").permitAll()
//            .antMatchers("/authentication/require",
//                    securityProperties.getBrowserProperties().getLoginPage(),
//                    "/code/*",
//                    "/error").permitAll()
//            .anyRequest().authenticated() //验证码
//            .and().csrf().disable()
//    .apply(smsCodeAuthenticationSecurityConfig); //关闭csrf防护
//
//  }
//
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//    // 使用密码加密
////    auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
//    auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
//  }
//}
