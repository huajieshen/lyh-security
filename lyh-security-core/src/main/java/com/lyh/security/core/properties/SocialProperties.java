package com.lyh.security.core.properties;

/**
 * @author shenhj
 * @version 1.0
 * Description:
 * @date 2019/12/11 0:03
 */
public class SocialProperties {
  private String filterProcessesUrl = "/auth";

  private QQProperties qq = new QQProperties();

  private WeiXinProperties weixin = new WeiXinProperties();



  public QQProperties getQq() {
    return qq;
  }

  public void setQq(QQProperties qq) {
    this.qq = qq;
  }

  public String getFilterProcessesUrl() {
    return filterProcessesUrl;
  }

  public void setFilterProcessesUrl(String filterProcessesUrl) {
    this.filterProcessesUrl = filterProcessesUrl;
  }

  public WeiXinProperties getWeixin() {
    return weixin;
  }

  public void setWeixin(WeiXinProperties weixin) {
    this.weixin = weixin;
  }
}
