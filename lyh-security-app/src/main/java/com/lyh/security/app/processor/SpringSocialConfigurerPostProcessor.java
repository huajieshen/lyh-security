package com.lyh.security.app.processor;

import com.lyh.security.core.social.LyhSpringSocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author shenhj
 * @version 1.0
 * Description:
 * @date 2019/12/26 0:33
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {

  /***
   * spring启动时所有的bean初始化之前都会调用该方法 --- 可以在bean初始化之前对bean做一些操作
   * @param bean
   * @param beanName
   * @return
   * @throws BeansException
   */
  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    return bean;
  }

  /***
   * spring启动时所有的bean初始化之后都会调用该方法 --- 可以对初始化好的bean做一些修改
   * @param bean
   * @param beanName
   * @return
   * @throws BeansException
   */
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if(StringUtils.equals(beanName, "lyhSocialSecurityConfig")){
      LyhSpringSocialConfigurer configurer = (LyhSpringSocialConfigurer) bean;
      configurer.signupUrl("/social/signUp");
      return configurer;
    }
    return bean;
  }
}
