package com.lyh.security.core.social.qq.connect;

import com.lyh.security.core.social.qq.api.QQ;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/10 16:52
 * Description: 组装ConnectionFactory对象---》ConnectionFactory对象由ServiceProvider和Adapter对象组成
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

  /**
   * 除了ServiceProvider和Adapter对象还需要一个providerId---》提供商的唯一标识
   * @param providerId
   * @param appId
   * @param appSecret
   */
//  public QQConnectionFactory(String providerId, OAuth2ServiceProvider<QQ> serviceProvider, ApiAdapter<QQ> apiAdapter) {
  public QQConnectionFactory(String providerId, String appId, String appSecret) {
//    super(providerId, serviceProvider, apiAdapter);
    super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
  }
}
