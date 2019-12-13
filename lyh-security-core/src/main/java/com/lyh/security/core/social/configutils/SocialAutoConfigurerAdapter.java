package com.lyh.security.core.social.configutils;

import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author shenhj
 * @version 1.0
 * Description: 从springboot1.5.6.RELEASE版本的org.springframework.boot.autoconfigure.social下拷贝的源码
 * @date 2019/12/10 23:24
 */
public abstract class SocialAutoConfigurerAdapter extends SocialConfigurerAdapter {
  @Override
  public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
//    super.addConnectionFactories(configurer, environment);
    configurer.addConnectionFactory(createConnectionFactory());
  }

  protected abstract ConnectionFactory<?> createConnectionFactory();
}
