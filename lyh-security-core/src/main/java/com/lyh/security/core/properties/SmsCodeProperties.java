package com.lyh.security.core.properties;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/4 21:19
 */
public class SmsCodeProperties {


  private int length = 6;
  private int expireIn = 60;

  private String url;

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public int getExpireIn() {
    return expireIn;
  }

  public void setExpireIn(int expireIn) {
    this.expireIn = expireIn;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
