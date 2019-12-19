package com.lyh.security.config;

import com.lyh.security.core.properties.SecurityProperties;
import com.lyh.security.session.LyhExpiredSessionStrategy;
import com.lyh.security.session.LyhInvalidSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * @author shenhj
 * @version 1.0
 * Description:
 * @date 2019/12/19 23:42
 */
@Configuration
public class SecurityBeanConfig {
  @Autowired
  private SecurityProperties securityProperties;

  @Bean
  //用户可以通过实现一个InvalidSessionStrategy类型的bean来覆盖掉默认的实现--》NRSCInvalidSessionStrategy
  @ConditionalOnMissingBean(InvalidSessionStrategy.class)
  public InvalidSessionStrategy invalidSessionStrategy(){
    return new LyhInvalidSessionStrategy(securityProperties.getBrowserProperties().getSession().getSessionInvalidUrl());
  }

  @Bean
  @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
  public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
    return new LyhExpiredSessionStrategy(securityProperties.getBrowserProperties().getSession().getSessionInvalidUrl());
  }


}
