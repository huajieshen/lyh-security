package com.lyh.security.core.properties;

/**
 * @author shenhj
 * @version 1.0
 * Description:
 * @date 2019/12/26 18:00
 */
public class OAuth2Properties {

  private String jwtSigningKey = "lyh";

  private OAuth2ClientProperties[] clients = {};

  public OAuth2ClientProperties[] getClients() {
    return clients;
  }

  public void setClients(OAuth2ClientProperties[] clients) {
    this.clients = clients;
  }

  public String getJwtSigningKey() {
    return jwtSigningKey;
  }

  public void setJwtSigningKey(String jwtSigningKey) {
    this.jwtSigningKey = jwtSigningKey;
  }
}
