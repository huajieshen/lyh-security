package com.lyh.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/20 21:40
 */
public class UserQueryCondition {

  private String username;

//  @ApiModelProperty(value = "用户年龄起始值")
  private int age;

//  @ApiModelProperty(value = "用户年龄终止值")
  private int ageTo;
  private String xxx;

  public int getAgeTo() {
    return ageTo;
  }

  public void setAgeTo(int ageTo) {
    this.ageTo = ageTo;
  }

  public String getXxx() {
    return xxx;
  }

  public void setXxx(String xxx) {
    this.xxx = xxx;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
