package com.lyh.security.core.social.qq.config;

import com.lyh.security.core.properties.QQProperties;
import com.lyh.security.core.properties.SecurityProperties;
import com.lyh.security.core.social.configutils.SocialAutoConfigurerAdapter;
import com.lyh.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author shenhj
 * @version 1.0
 * Description:
 * @date 2019/12/10 23:11
 */
@Configuration
@ConditionalOnProperty(prefix = "lyh.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

  @Autowired
  private SecurityProperties securityProperties;

  @Override
  protected ConnectionFactory<?> createConnectionFactory() {
    QQProperties qqConfig = securityProperties.getSocial().getQq();
    return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
  }
}
