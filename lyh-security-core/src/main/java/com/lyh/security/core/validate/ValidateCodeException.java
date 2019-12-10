package com.lyh.security.core.validate;

import org.springframework.security.core.AuthenticationException;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/3 0:23
 */
public class ValidateCodeException extends AuthenticationException {

  private static final long serialVersionUID = 1L;

  public ValidateCodeException(String msg) {
    super(msg);
  }
}
