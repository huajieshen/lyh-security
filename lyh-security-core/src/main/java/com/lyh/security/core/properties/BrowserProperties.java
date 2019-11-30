package com.lyh.security.core.properties;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/29 14:40
 */
public class BrowserProperties {

  // 默认值
  private String loginPage = "/demo-signIn.html";

  public String getLoginPage() {
    return loginPage;
  }

  public void setLoginPage(String loginPage) {
    this.loginPage = loginPage;
  }
}
