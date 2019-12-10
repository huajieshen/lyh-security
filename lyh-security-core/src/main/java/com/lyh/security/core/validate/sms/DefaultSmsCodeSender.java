package com.lyh.security.core.validate.sms;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/6 16:04
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
  @Override
  public void send(String mobile, String code) {
    System.out.println("向手机" + mobile + "发送短信验证码" + code);
  }
}
