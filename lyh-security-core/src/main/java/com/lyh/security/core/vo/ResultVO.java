package com.lyh.security.core.vo;

import java.io.Serializable;

/**
 * @author shenhj
 * @version 1.0
 * Description:
 * @date 2019/12/19 14:17
 */
public class ResultVO<T> implements Serializable {
  private static final long serialVersionUID = -1L;

  /** 错误码. */
  private Integer code;

  /** 提示信息. */
  private String msg;

  /** 具体内容. */
  private T data;

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
