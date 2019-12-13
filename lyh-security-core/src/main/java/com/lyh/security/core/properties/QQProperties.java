package com.lyh.security.core.properties;

import com.lyh.security.core.social.configutils.SocialProperties;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/10 19:15
 */
public class QQProperties extends SocialProperties {

  private String providerId = "qq";

  public String getProviderId() {
    return providerId;
  }

  public void setProviderId(String providerId) {
    this.providerId = providerId;
  }
}
