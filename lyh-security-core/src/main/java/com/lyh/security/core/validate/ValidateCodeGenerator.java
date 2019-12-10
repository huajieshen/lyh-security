package com.lyh.security.core.validate;


import org.springframework.web.context.request.ServletWebRequest;


/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/5 12:23
 */
public interface ValidateCodeGenerator {

  ValidateCode createCode(ServletWebRequest request);

}
