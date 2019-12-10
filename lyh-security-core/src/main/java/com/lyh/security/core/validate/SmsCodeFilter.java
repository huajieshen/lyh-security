//package com.lyh.security.core.validate;
//
//import com.lyh.security.core.properties.SecurityProperties;
//import com.lyh.security.core.validate.image.ImageCode;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.social.connect.web.HttpSessionSessionStrategy;
//import org.springframework.social.connect.web.SessionStrategy;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.web.bind.ServletRequestBindingException;
//import org.springframework.web.bind.ServletRequestUtils;
//import org.springframework.web.context.request.ServletWebRequest;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @author shenhj
// * @version 1.0
// * @date 2019/12/7 0:00
// */
//public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {
//
//  private AuthenticationFailureHandler authenticationFailureHandler;
//
//  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
//
//  private Set<String> urls = new HashSet<>();
//
//  // 视频中没有注入注解，这个类报空指针错误，个人认为是SecurityProperties相互依赖了，要先生成bean注入
//  @Autowired
//  private SecurityProperties securityProperties;
//
//  private AntPathMatcher pathMatcher = new AntPathMatcher();
//
//  @Override
//  public void afterPropertiesSet() throws ServletException {
//    super.afterPropertiesSet();
//    if(securityProperties.getCodeProperties().getImage().getUrl() != null) {
//      String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCodeProperties().getSms().getUrl(), ",");
//
//      for (String configUrl : configUrls) {
//        urls.add(configUrl);
//      }
//    }
//
//    urls.add("/authentication/mobile");
//  }
//
//  @Override
//  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//
//    boolean action = false;
//    for (String url : urls) {
//      if (pathMatcher.match(url, request.getRequestURI())) {
//        action = true;
//      }
//    }
//
////    if(StringUtils.equals("/authentication/form", request.getRequestURI())
////       && StringUtils.equalsIgnoreCase(request.getMethod(), "post")){
//
//    if (action) {
//      try {
//        validate(new ServletWebRequest(request));
//      } catch (ValidateCodeException e) {
//        authenticationFailureHandler.onAuthenticationFailure(request, response, e);
//        return;
//      }
//    }
//    filterChain.doFilter(request, response);
//  }
//
//  private void validate(ServletWebRequest request) throws ServletRequestBindingException {
//    ValidateCode codeInSession = (ValidateCode) sessionStrategy.getAttribute(request,
//            ValidateCodeProcessor.SESSION_KEY_PREFIX + "SMS");
//
//    String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "smsCode");
//
//    if (StringUtils.isEmpty(codeInRequest)) {
//      throw new ValidateCodeException("验证码不能为空");
//    }
//
//    if (codeInSession == null) {
//      throw new ValidateCodeException("验证码不存在");
//    }
//
//    if (codeInSession.isExpired()) {
//      sessionStrategy.removeAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX + "SMS");
//      throw new ValidateCodeException("验证码已过期");
//    }
//
//    if (!codeInRequest.equals(codeInSession.getCode())) {
//      throw new ValidateCodeException("验证码不匹配");
//    }
//
//    sessionStrategy.removeAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX + "SMS");
//  }
//
//  public AuthenticationFailureHandler getAuthenticationFailureHandler() {
//    return authenticationFailureHandler;
//  }
//
//  public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
//    this.authenticationFailureHandler = authenticationFailureHandler;
//  }
//
//  public SessionStrategy getSessionStrategy() {
//    return sessionStrategy;
//  }
//
//  public void setSessionStrategy(SessionStrategy sessionStrategy) {
//    this.sessionStrategy = sessionStrategy;
//  }
//
//  public SecurityProperties getSecurityProperties() {
//    return securityProperties;
//  }
//
//  public void setSecurityProperties(SecurityProperties securityProperties) {
//    this.securityProperties = securityProperties;
//  }
//}
