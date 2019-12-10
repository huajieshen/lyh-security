package com.lyh.security.core.properties;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/4 21:36
 */
public class ValidateCodeProperties {
  private ImageCodeProperties image = new ImageCodeProperties();

  private SmsCodeProperties sms = new SmsCodeProperties();

  public ImageCodeProperties getImage() {
    return image;
  }

  public void setImage(ImageCodeProperties image) {
    this.image = image;
  }

  public SmsCodeProperties getSms() {
    return sms;
  }

  public void setSms(SmsCodeProperties sms) {
    this.sms = sms;
  }
}
