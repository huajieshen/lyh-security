package com.lyh.security.core.properties;

import com.lyh.security.core.constant.SecurityConstants;
import com.lyh.security.core.enums.LoginResponseType;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/29 14:40
 */
public class BrowserProperties {

  /**指定默认的注册页面*/
  private String signUpUrl = "/lyh-signUp.html";

  /**指定默认的登陆页面*/
  private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

  /**指定默认的处理成功与处理失败的方法*/
  private LoginResponseType loginResponseType = LoginResponseType.JSON;

  /**记住我的时间3600秒即1小时*/
  private int rememberMeSeconds = 3600;

  /**session相关的配置如session最大并发数量等*/
  private SessionProperties session = new SessionProperties();




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

  public String getSignUpUrl() {
    return signUpUrl;
  }

  public void setSignUpUrl(String signUpUrl) {
    this.signUpUrl = signUpUrl;
  }

  public SessionProperties getSession() {
    return session;
  }

  public void setSession(SessionProperties session) {
    this.session = session;
  }
}
