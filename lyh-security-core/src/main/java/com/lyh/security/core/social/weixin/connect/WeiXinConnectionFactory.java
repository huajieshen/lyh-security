package com.lyh.security.core.social.weixin.connect;

import com.lyh.security.core.social.weixin.api.WeiXin;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * @author shenhj
 * @date 2019/12/19 11:48
 * @version 1.0
 * Description:
 * 微信连接工厂
 */
public class WeiXinConnectionFactory extends OAuth2ConnectionFactory<WeiXin> {

  public WeiXinConnectionFactory(String providerId, String appId, String appSecret) {
    super(providerId, new WeiXinServiceProvider(appId, appSecret), new WeiXinAdapter());
  }


  @Override
  public Connection<WeiXin> createConnection(AccessGrant accessGrant) {
    return new OAuth2Connection<WeiXin>(getProviderId(),
            extractProviderUserId(accessGrant),
            accessGrant.getAccessToken(),
            accessGrant.getRefreshToken(),
            accessGrant.getExpireTime(),
            getOAuth2ServiceProvider(),
            getApiAdapter(extractProviderUserId(accessGrant)));
  }


  /**
   * 由于微信的openId是和accessToken一起返回的，所以在这里直接根据accessToken设置providerUserId即可，不用像QQ那样通过QQAdapter来获取
   */
  @Override
  protected String extractProviderUserId(AccessGrant accessGrant) {
    if (accessGrant instanceof WeiXinAccessGrant) {
      return ((WeiXinAccessGrant) accessGrant).getOpenId();
    }
    return null;
  }


  @Override
  public Connection<WeiXin> createConnection(ConnectionData data) {
    return new OAuth2Connection<WeiXin>(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
  }


  /**
   * WeiXinAdapter应该是多实例的----providerUserId即openId不一样，便不是一个用户
   *
   * @param extractProviderUserId
   * @return
   */
  private ApiAdapter<WeiXin> getApiAdapter(String extractProviderUserId) {
    return new WeiXinAdapter(extractProviderUserId);
  }

  private OAuth2ServiceProvider<WeiXin> getOAuth2ServiceProvider(){
    return (OAuth2ServiceProvider<WeiXin>) getServiceProvider();
  }


}
