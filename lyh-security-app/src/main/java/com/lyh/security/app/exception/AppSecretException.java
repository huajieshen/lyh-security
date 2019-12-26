package com.lyh.security.app.exception;

/**
 * @author shenhj
 * @version 1.0
 * Description: app异常
 * @date 2019/12/26 0:11
 */
public class AppSecretException extends RuntimeException{
  public AppSecretException(String msg) {
    super(msg);
  }
}
