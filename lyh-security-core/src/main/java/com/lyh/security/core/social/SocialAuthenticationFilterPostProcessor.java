package com.lyh.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @author shenhj
 * @version 1.0
 * Description: 指定springsocial成功处理器的接口
 * @date 2019/12/25 23:23
 */

public interface SocialAuthenticationFilterPostProcessor {
  /**
   * 参数为springsocial的过滤器
   * @param socialAuthenticationFilter
   */
  void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
