package com.lyh.security.app.config;

import com.lyh.security.core.properties.OAuth2ClientProperties;
import com.lyh.security.core.properties.SecurityProperties;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenhj
 * @version 1.0
 * Description: 认证服务器
 * @date 2019/12/20 14:59
 */

@Configuration
@EnableAuthorizationServer
public class LyhAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

//
//  @Autowired
//  private DataSource dataSource;
//
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService customUserDetailsService;

  @Autowired
  private SecurityProperties securityProperties;

  @Autowired
//  private TokenStore redisTokenStore;
  private TokenStore tokenStore;

  @Autowired(required = false)
  private JwtAccessTokenConverter jwtAccessTokenConverter;

  @Autowired(required = false)
  private TokenEnhancer jwtTokenEnhancer;

  //  @Override
//  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//    security.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
//  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
//            .tokenStore(redisTokenStore)
            .tokenStore(tokenStore)
            //下面的配置主要用来指定"对正在进行授权的用户进行认证+校验"的类
            //在实现了AuthorizationServerConfigurerAdapter适配器类后，必须指定下面两项
            .authenticationManager(authenticationManager)
            .userDetailsService(customUserDetailsService);

    if(jwtAccessTokenConverter != null && jwtTokenEnhancer != null){
      TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
      List<TokenEnhancer> enhancers = new ArrayList<>();
      enhancers.add(jwtTokenEnhancer);
      enhancers.add(jwtAccessTokenConverter);
      enhancerChain.setTokenEnhancers(enhancers);

      endpoints
              .tokenEnhancer(enhancerChain)
              .accessTokenConverter(jwtAccessTokenConverter);
    }
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//    clients.jdbc(dataSource);

    InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
    if(ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())){

      for(OAuth2ClientProperties config: securityProperties.getOauth2().getClients()){
        builder.withClient(config.getClientId())
                .secret(config.getClientSecret())
                .accessTokenValiditySeconds(config.getAccessTokenValiditySeconds())
                .authorizedGrantTypes("authorization_code", "refresh_token", "password")//授权方式
                .refreshTokenValiditySeconds(2592000)
                .scopes("all", "read", "write"); //授权范围
      }
    }
//        clients.inMemory()
//        .withClient("lyh")//用于标识用户ID
//            .accessTokenValiditySeconds(7200)
//            .authorizedGrantTypes("authorization_code", "refresh_token", "password")//授权方式
//            .scopes("all", "read", "write")//授权范围
////            .secret(new BCryptPasswordEncoder().encode("lyhSecret"))
//            .secret("lyhSecret")
////            .autoApprove(false)
//            .redirectUris("http://example.com");

//    clients.withClientDetails(clientDetails());
  }

//  @Bean
//  public ClientDetailsService clientDetails() {
//    return new JdbcClientDetailsService(dataSource);
//  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security
            .allowFormAuthenticationForClients()
            .checkTokenAccess("permitAll()")
            .passwordEncoder(NoOpPasswordEncoder.getInstance());
  }
}
