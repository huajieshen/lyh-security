package com.lyh.exception;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/25 16:02
 */
public class UserNotExistException extends RuntimeException{

  private static final long serialVersionUID = 1L;

  private Integer id;

  public UserNotExistException(Integer id){
    super("user not exist");
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
