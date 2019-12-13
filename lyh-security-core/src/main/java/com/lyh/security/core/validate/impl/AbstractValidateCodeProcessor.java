package com.lyh.security.core.validate.impl;

import com.lyh.security.core.validate.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/6 17:21
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

  /**
   * 操作session的工具类
   */
  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

  /**
   * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
   * spring 启东的时候会查找ValidateCodeGenerator接口的实现，找到后把相应的bean的名字作为key，放到map里面去
   */
  @Autowired
  private Map<String, ValidateCodeGenerator> validateCodeGenerators;

  @Override
  public void create(ServletWebRequest request) throws Exception {
    // 1. 创建验证码
    C validateCode = generate(request);
    // 2. 保存验证码
    save(request, validateCode);
    // 2. 发送或者输出验证码
    send(request, validateCode);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void validate(ServletWebRequest request) {

    ValidateCodeType processorType = getValidateCodeType(request);
    String sessionKey = getSessionKey(request);

    C codeInSession = (C) sessionStrategy.getAttribute(request, sessionKey);

    String codeInRequest;
    try {
      codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
              processorType.getParamNameOnValidate());
    } catch (ServletRequestBindingException e) {
      throw new ValidateCodeException("获取验证码的值失败");
    }

    if (StringUtils.isBlank(codeInRequest)) {
      throw new ValidateCodeException(processorType + "验证码的值不能为空");
    }

    if (codeInSession == null) {
      throw new ValidateCodeException(processorType + "验证码不存在");
    }

    if (codeInSession.isExpired()) {
      sessionStrategy.removeAttribute(request, sessionKey);
      throw new ValidateCodeException(processorType + "验证码已过期");
    }

    if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
      throw new ValidateCodeException(processorType + "验证码不匹配");
    }

    sessionStrategy.removeAttribute(request, sessionKey);
  }

  /**
   * 生成校验码
   *
   * @param request
   * @return
   */
  @SuppressWarnings("unchecked")
  private C generate(ServletWebRequest request) {

//    String type = getProcessorType(request);
//    ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type + "CodeGenerator");
//    return (C) validateCodeGenerator.createCode(request);

    String type = getValidateCodeType(request).toString().toLowerCase();
    String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
    ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
    if(validateCodeGenerator == null){
      throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
    }
    return (C) validateCodeGenerator.createCode(request);
  }

  /**
   * 保存校验码
   *
   * @param request
   * @param validateCode
   */
  private void save(ServletWebRequest request, C validateCode) {
//    sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(), validateCode);
    sessionStrategy.setAttribute(request, getSessionKey(request), validateCode);
  }

  /**
   * 构建验证码放入 session 时候的 key
   *
   * @param request
   * @return
   */
  private String getSessionKey(ServletWebRequest request) {
    return SESSION_KEY_PREFIX + getValidateCodeType(request).toString().toUpperCase();
  }

  /**
   * 发送校验码，由子类实现
   *
   * @param request
   * @param validateCode
   * @throws Exception
   */
  protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

  /**
   * 根据请求的url获取校验码的类型
   *
   * @param request
   * @return
   */
  private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
//    System.out.println(getClass().getSimpleName());
    String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
    return ValidateCodeType.valueOf(type.toUpperCase());
  }

//  private String getProcessorType(ServletWebRequest request) {
//    return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
//  }


}
