package com.lyh.security.support;

/**
 * @author shenhj
 * @version 1.0
 * Description:
 * @date 2019/12/11 17:06
 */
public class SocialUserInfo {
  private String providerId;

  private String providerUserId;

  private String nickname;

  private String headimg;

  public String getProviderId() {
    return providerId;
  }

  public void setProviderId(String providerId) {
    this.providerId = providerId;
  }

  public String getProviderUserId() {
    return providerUserId;
  }

  public void setProviderUserId(String providerUserId) {
    this.providerUserId = providerUserId;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getHeadimg() {
    return headimg;
  }

  public void setHeadimg(String headimg) {
    this.headimg = headimg;
  }
}