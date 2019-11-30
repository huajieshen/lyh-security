package com.lyh.security.config;

import com.lyh.security.core.properties.SecurityProperties;
import com.lyh.security.service.CustomuserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/28 19:15
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  // 处理密码加密解密
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Autowired
  private CustomuserDetailsService customuserDetailsService;

  @Autowired
  private SecurityProperties securityProperties;


  @Override
  protected void configure(HttpSecurity http) throws Exception {

//    http.authorizeRequests()
//            .anyRequest().permitAll().and().logout().permitAll();//配置不需要登录验证

    http
            .formLogin()
//            .loginPage("/login.html")
            .loginPage("/authentication/require")
            .loginProcessingUrl("/authentication/form")
            .and()
            .authorizeRequests()
//            .antMatchers("/login.html").permitAll()
            .antMatchers("/authentication/require",
                    securityProperties.getBrowserProperties().getLoginPage()).permitAll()
            .anyRequest().authenticated()
            .and().csrf().disable();

  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    // 使用密码加密
//    auth.userDetailsService(customuserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    auth.userDetailsService(customuserDetailsService).passwordEncoder(passwordEncoder());
  }
}
