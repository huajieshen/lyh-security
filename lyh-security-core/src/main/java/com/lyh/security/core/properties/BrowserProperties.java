package com.lyh.security.core.properties;

import com.lyh.security.core.enums.LoginResponseType;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/29 14:40
 */
public class BrowserProperties {

  // 默认值
  private String loginPage = "/login-signIn.html";

  private LoginResponseType loginResponseType = LoginResponseType.JSON;

  private int rememberMeSeconds = 3600;

  public String getLoginPage() {
    return loginPage;
  }

  public void setLoginPage(String loginPage) {
    this.loginPage = loginPage;
  }

  public LoginResponseType getLoginResponseType() {
    return loginResponseType;
  }

  public void setLoginResponseType(LoginResponseType loginResponseType) {
    this.loginResponseType = loginResponseType;
  }

  public int getRememberMeSeconds() {
    return rememberMeSeconds;
  }

  public void setRememberMeSeconds(int rememberMeSeconds) {
    this.rememberMeSeconds = rememberMeSeconds;
  }
}
