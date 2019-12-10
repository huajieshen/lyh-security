package com.lyh.security.core.validate.sms;

import com.lyh.security.core.validate.ValidateCode;
import com.lyh.security.core.validate.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/6 21:06
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

  /**
   * 短信验证码发送器
   */
  @Autowired
  private SmsCodeSender smsCodeSender;

  @Override
  protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
    String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
    smsCodeSender.send(mobile, validateCode.getCode());
  }

  @Override
  public void validate(ServletWebRequest request) {

  }
}
