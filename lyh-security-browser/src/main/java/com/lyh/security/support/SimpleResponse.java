package com.lyh.security.support;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/29 14:19
 */
public class SimpleResponse {

  private Object content;

  public SimpleResponse(Object content) {
    this.content = content;
  }

  public Object getContent() {
    return content;
  }

  public void setContent(Object content) {
    this.content = content;
  }
}
