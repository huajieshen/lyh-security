package com.lyh.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/29 14:40
 */

@Component
@ConfigurationProperties(prefix = "lyh.security")
public class SecurityProperties {
  private BrowserProperties browserProperties = new BrowserProperties();

  public BrowserProperties getBrowserProperties() {
    return browserProperties;
  }

  public void setBrowserProperties(BrowserProperties browserProperties) {
    this.browserProperties = browserProperties;
  }
}
