package com.lyh.security.browser;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/28 21:28
 * 处理用户信息获取逻辑
 */
//@Slf4j
@Component("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService, SocialUserDetailsService {


  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private PasswordEncoder passwordEncoder;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // 处理用户校验

    logger.info("表单登录用户名： " + username);
    return buildUser(username);


    // 根据用户名查找用户信息
    // 根据查找到的用户信息判断用户是否被冻结

//    // 密码加密
//    String password = passwordEncoder.encode("123456");
//    logger.info("数据库密码是： " + password);


    // 密码必须要加密
//    return new User(username, new BCryptPasswordEncoder().encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

//    return new User(username, new BCryptPasswordEncoder().encode("123456"),
//    return new User(username, password,
//            true, true, true, true,
//            AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
  }


  @Override
  public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {

    logger.info("设计登录用户Id： " + userId);

   return buildUser(userId);

  }

  private SocialUserDetails buildUser(String userId){
    String password = passwordEncoder.encode("123456");
    logger.info("数据库密码是： " + password);
    return new SocialUser(userId, password, true, true, true, true,
            AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
  }

}
