package com.lyh.security.core.pojo;

/**
 * @author shenhj
 * @version 1.0
 * Description:
 * @date 2019/12/11 17:06
 */
public class SocialUserInfo {

  /**提供商唯一标识*/
  private String providerId;

  /***用户在提供商的唯一标识（其实就是openId）*/
  private String providerUserId;

  /**用户在提供商的昵称*/
  private String nickName;

  /**用户在提供商的头像*/
  private String headImg;

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

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getHeadImg() {
    return headImg;
  }

  public void setHeadImg(String headImg) {
    this.headImg = headImg;
  }
}
