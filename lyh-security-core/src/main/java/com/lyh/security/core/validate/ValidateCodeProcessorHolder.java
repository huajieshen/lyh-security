package com.lyh.security.core.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/7 1:32
 */
@Component
public class ValidateCodeProcessorHolder {

  @Autowired
  private Map<String, ValidateCodeProcessor> validateCodeProcessors;

  public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type){
//    System.out.println(findValidateCodeProcessor(type.toString().toLowerCase()));
    return findValidateCodeProcessor(type.toString().toLowerCase());
  }

  public ValidateCodeProcessor findValidateCodeProcessor(String type){
    String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
    ValidateCodeProcessor processor = validateCodeProcessors.get(name);
    if(processor == null){
      throw new ValidateCodeException("验证码处理器" + name + "不存在");
    }
    return processor;
  }

}
