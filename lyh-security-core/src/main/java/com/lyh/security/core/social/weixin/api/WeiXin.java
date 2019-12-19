package com.lyh.security.core.social.weixin.api;

/**
 * @author shenhj
 * @version 1.0
 * Description:
 * @date 2019/12/18 23:55
 */
public interface WeiXin {

  // QQ是获取完accessToken，然后拿着accessToken去换openId
  // 微信是在获取accessToken的同时也会获取到openId
  WeiXinUserInfo getUserInfo(String openId);
}
