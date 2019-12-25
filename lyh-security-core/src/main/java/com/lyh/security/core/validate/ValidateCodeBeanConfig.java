package com.lyh.security.core.validate;

import com.lyh.security.core.validate.sms.DefaultSmsCodeSender;
import com.lyh.security.core.validate.sms.SmsCodeSender;
import com.lyh.security.core.properties.SecurityProperties;
import com.lyh.security.core.validate.image.ImageCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/5 12:51
 */
@Configuration
public class ValidateCodeBeanConfig {

  @Autowired
  private SecurityProperties securityProperties;

  @Bean(name = "imageValidateCodeGenerator")
//  @ConditionalOnMissingBean(name = "imageCodeGenerator")
  @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
  // spring 启东时不存在这样的bean时，才启用以下的配置
  public ValidateCodeGenerator imageCodeGenerator() {
    ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
    codeGenerator.setSecurityProperties(securityProperties);
    return codeGenerator;
  }

  @Bean
//  @ConditionalOnMissingBean(name = "smsCodeSender")
  @ConditionalOnMissingBean(SmsCodeSender.class)
  public SmsCodeSender smsCodeSender() {
    return new DefaultSmsCodeSender();
  }

}
