package com.lyh.security.app.config;

import com.lyh.security.app.jwt.LyhJwtTokenEnhancer;
import com.lyh.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author shenhj
 * @version 1.0
 * Description:
 * @date 2019/12/26 17:44
 */
@Configuration
public class TokenStoreConfig {

  @Autowired
  private RedisConnectionFactory redisConnectionFactory;

  /***
   * 将RedisTokenStore注入到spring容器
   * 当yml配置文件里配置了lyh.security.oauth2.tokenStoreType = redis时 ---> 下面的配置生效
   * @return
   */
  @Bean
  @ConditionalOnProperty(prefix = "lyh.security.oauth2", name = "tokenStoreType", havingValue = "redis")
  public TokenStore redisTokenStore(){
    return new RedisTokenStore(redisConnectionFactory);
  }

  /***
   * 当yml配置文件里配置了lyh.security.oauth2.tokenStoreType = jwt或者根本就没配置该属性时 ---> 下面的配置生效
   */
  @Configuration
  @ConditionalOnProperty(prefix = "lyh.security.oauth2", name = "tokenStoreType", havingValue = "jwt", matchIfMissing = true)
  public static class JwtConfig{

    @Autowired
    private SecurityProperties securityProperties;


    /***
     * 配置JwtTokenStore ---> TokenStore只负责token的存储，不负责token的生成
     * @return
     */
    @Bean
    public TokenStore JwtTokenStore(){
      return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /***
     * JwtAccessTokenConverter 其实就是一个TokenEnhancer
     * 通过阅读源码可知：TokenEnhancer是对生成的Token进行后续处理的（或者说增强），
     * 其实JwtAccessTokenConverter就是将默认生成的token做进一步处理使其成为一个JWT
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
      JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
      //加入密签 --- 一定要保护好
      accessTokenConverter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());
      return accessTokenConverter;
    }

    /***
     * 将自定义的TokenEnhancer注入到spring容器 --- 》可以覆盖该bean，实现自己的需求
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
    public TokenEnhancer jwtTokenEnhancer(){
      return new LyhJwtTokenEnhancer();
    }
  }




}
