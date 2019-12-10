package com.lyh.security.core.validate.sms;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/6 15:49
 */
public interface SmsCodeSender {
  void send(String mobile, String code);
}
