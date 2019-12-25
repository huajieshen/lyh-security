package com.lyh.security.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author shenhj
 * @version 1.0
 * Description: 认证服务器
 * @date 2019/12/20 14:59
 */

@Configuration
@EnableAuthorizationServer
public class LyhAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

//  @Autowired
//  private UserDetailsService customUserDetailsService;
//
//  @Autowired
//  private DataSource dataSource;
//
//  @Autowired
//  private AuthenticationManager authenticationManager;


  //  @Override
//  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//    security.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
//  }
//
//  @Override
//  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//    endpoints
//            //下面的配置主要用来指定"对正在进行授权的用户进行认证+校验"的类
//            //在实现了AuthorizationServerConfigurerAdapter适配器类后，必须指定下面两项
//            .authenticationManager(authenticationManager)
//            .userDetailsService(customUserDetailsService);
//  }
//
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//    clients.jdbc(dataSource);
    clients.inMemory()
            .withClient("lyh")//用于标识用户ID
            .authorizedGrantTypes("authorization_code", "refresh_token")//授权方式
            .scopes("all")//授权范围
//            .secret(new BCryptPasswordEncoder().encode("lyhSecret"))
            .secret("lyhSecret")
//            .autoApprove(false)
            .redirectUris("http://example.com");

//    clients.withClientDetails(clientDetails());
  }

//  @Bean
//  public ClientDetailsService clientDetails() {
//    return new JdbcClientDetailsService(dataSource);
//  }
}
