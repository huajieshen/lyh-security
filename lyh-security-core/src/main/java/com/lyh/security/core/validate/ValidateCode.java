package com.lyh.security.core.validate;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/5 20:29
 */
public class ValidateCode implements Serializable {

  //    验证码
  private String code;
  //    过期时间
  private LocalDateTime expireTime;

  public ValidateCode(String code, int expireIn) {
    this.code = code;
    this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
  }

  public ValidateCode(String code, LocalDateTime expireTime) {
    this.code = code;
    this.expireTime = expireTime;
  }

  //判断验证码是否过期
  public boolean isExpired(){
    return LocalDateTime.now().isAfter(expireTime);
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public LocalDateTime getExpireTime() {
    return expireTime;
  }

  public void setExpireTime(LocalDateTime expireTime) {
    this.expireTime = expireTime;
  }
}
