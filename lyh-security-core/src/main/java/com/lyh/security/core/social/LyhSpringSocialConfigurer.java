package com.lyh.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author shenhj
 * @version 1.0
 * Description: 继承springsocialSocial为我们提供的SocialAuthenticationFilter的配置类SpringSocialConfigurer
 * 并重写其后置处理方法，让其默认拦截包含我们配置字符串的请求
 * @date 2019/12/11 11:06
 */
public class LyhSpringSocialConfigurer extends SpringSocialConfigurer {

  private String filterProcessesUrl;

  public LyhSpringSocialConfigurer(String filterProcessesUrl){
     this.filterProcessesUrl = filterProcessesUrl;
  }

  @Override
  protected <T> T postProcess(T object) {
    //在父类处理完SocialAuthenticationFilter之后的基础上，将其默认拦截url改成我们配置的值
    SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
    filter.setFilterProcessesUrl(filterProcessesUrl);
    return (T) filter;
  }
}
