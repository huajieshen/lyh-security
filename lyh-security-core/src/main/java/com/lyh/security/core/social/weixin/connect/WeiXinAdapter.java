package com.lyh.security.core.social.weixin.connect;

import com.lyh.security.core.social.weixin.api.WeiXin;
import com.lyh.security.core.social.weixin.api.WeiXinUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author shenhj
 * @version 1.0
 * Description:
 * @date 2019/12/19 11:37
 */
public class WeiXinAdapter implements ApiAdapter<WeiXin> {

  private String openId;

  public WeiXinAdapter(){

  }

  public WeiXinAdapter(String openId) {
    this.openId = openId;
  }

  @Override
  public boolean test(WeiXin api) {
    return true;
  }

  @Override
  public void setConnectionValues(WeiXin api, ConnectionValues values) {
    WeiXinUserInfo profile = api.getUserInfo(openId);
    values.setProviderUserId(profile.getOpenid());
    values.setDisplayName(profile.getNickname());
    values.setImageUrl(profile.getHeadimgurl());
  }

  @Override
  public UserProfile fetchUserProfile(WeiXin api) {
    return null;
  }

  @Override
  public void updateStatus(WeiXin api, String message) {

  }
}
