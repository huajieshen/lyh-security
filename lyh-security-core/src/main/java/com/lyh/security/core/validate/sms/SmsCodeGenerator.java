package com.lyh.security.core.validate.sms;

import com.lyh.security.core.properties.SecurityProperties;
import com.lyh.security.core.validate.ValidateCode;
import com.lyh.security.core.validate.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/6 16:23
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

  @Autowired
  private SecurityProperties securityProperties;

  @Override
//  public ValidateCode createCode(HttpServletRequest request) {
  public ValidateCode createCode(ServletWebRequest request) {
    String code = RandomStringUtils.randomNumeric(securityProperties.getCodeProperties().getSms().getLength());
    return new ValidateCode(code, securityProperties.getCodeProperties().getSms().getExpireIn());
  }

  public SecurityProperties getSecurityProperties() {
    return securityProperties;
  }

  public void setSecurityProperties(SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }
}
