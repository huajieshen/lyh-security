package com.lyh.dto;

import com.lyh.validator.MyConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/20 16:39
 */
public class User {
  private Integer id;
  @MyConstraint(message = "这是一个测试")
  private String username;

  @NotBlank(message = "密码不能为空")
  private String password;
  @Past(message = "生日必须是过去的时间")
  private Date birthday;

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
