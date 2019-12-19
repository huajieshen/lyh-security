package com.lyh.security.core.enums;

import lombok.Getter;

/**
 * @author shenhj
 * @version 1.0
 * Description:
 * @date 2019/12/19 23:06
 */
@Getter
public enum ResultEnum {

  SUCCESS(0, "成功"),
  UNAUTHORIZED(800, "访问的服务需要身份认证，请引导用户到登录页"),
  LOGIN_FAILURE(801,"认证失败"),
  SESSION_INVALID(802,"session失效");

  private Integer code;
  private String message;

  ResultEnum(Integer code, String message){
    this.code = code;
    this.message = message;
  }


}
