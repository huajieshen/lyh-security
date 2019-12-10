package com.lyh.security.core.validate;


import com.lyh.security.core.constant.SecurityConstants;
import com.lyh.security.core.properties.SecurityProperties;
import com.lyh.security.core.validate.image.ImageCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author shenhjValidateCodeFilter
 * @version 1.0
 * @date 2019/12/3 0:16
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

  /**
   * 验证码校验失败处理器
   */
  private AuthenticationFailureHandler authenticationFailureHandler;

  /**
   * 系统配置信息
   */
  @Autowired
  private SecurityProperties securityProperties;

  /**
   * 系统中的校验码处理器
   */
  @Autowired
  private ValidateCodeProcessorHolder validateCodeProcessorHolder;

  /**
   * 存放所有需要校验验证码的url
   */
  private Map<String, ValidateCodeType> urlMap = new HashMap<>();

  /**
   * 验证请求url与配置的url是否匹配的工具类
   */
  private AntPathMatcher pathMatcher = new AntPathMatcher();

  /**
   * 初始化要拦截的url配置信息
   */
  @Override
  public void afterPropertiesSet() throws ServletException {
    super.afterPropertiesSet();

    urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
    addUrlToMap(securityProperties.getCodeProperties().getImage().getUrl(), ValidateCodeType.IMAGE);

    urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
    addUrlToMap(securityProperties.getCodeProperties().getSms().getUrl(), ValidateCodeType.SMS);

  }

  /**
   * 系统中配置的需要校验验证码的URL根据校验的类型放入map
   *
   * @param urlString
   * @param type
   */
  protected void addUrlToMap(String urlString, ValidateCodeType type) {
    if (StringUtils.isNotBlank(urlString)) {
      String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
      for (String url : urls) {
        urlMap.put(url, type);
      }
    }
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {

    ValidateCodeType type = getValidateCodeType(request);
    if(type != null){
      logger.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + type);
      try{
        validateCodeProcessorHolder.findValidateCodeProcessor(type).validate(new ServletWebRequest(request, response));
        logger.info("验证码校验通过");
      }catch (ValidateCodeException e){
        authenticationFailureHandler.onAuthenticationFailure(request, response, e);
        return;
      }
    }
    filterChain.doFilter(request, response);
  }

  /**
   * 获取校验码的类型，如果当前请求不需要校验，则返回null
   * @param request
   * @return
   */
  private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
    ValidateCodeType result = null;
    System.out.println(request.getMethod());
    if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
      Set<String> urls = urlMap.keySet();
      for (String url : urls) {
        if (pathMatcher.match(url, request.getRequestURI())) {
          result = urlMap.get(url);
        }
      }
    }
    return result;
  }
}
