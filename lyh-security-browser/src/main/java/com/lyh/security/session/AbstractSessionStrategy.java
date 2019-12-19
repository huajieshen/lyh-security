package com.lyh.security.session;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyh.security.core.enums.ResultEnum;
import com.lyh.security.core.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shenhj
 * @version 1.0
 * Description:
 * @date 2019/12/19 22:38
 */

@Slf4j
public class AbstractSessionStrategy {
  /**
   * 跳转的url
   */
  private String destinationUrl;
  /**
   * 重定向策略
   */
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
  /**
   * 跳转前是否创建新的session
   */
  private boolean createNewSession = true;

  private ObjectMapper objectMapper = new ObjectMapper();

  /**
   *
   * @param invalidSessionUrl
   */
  public AbstractSessionStrategy(String invalidSessionUrl) {
    Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl),"url must start with '/' or with 'http(s)'");
    Assert.isTrue(StringUtils.endsWithIgnoreCase(invalidSessionUrl, ".html"), "url must end with '.html'");
    this.destinationUrl = invalidSessionUrl;
  }

  protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response)throws IOException{
    log.info("session失效");

    if(createNewSession){
      request.getSession();
    }

    String sourceUrl = request.getRequestURI();
    String targetUrl;

    if(StringUtils.endsWithIgnoreCase(sourceUrl, ".html")){
      targetUrl = destinationUrl;
      log.info("跳转到: " + targetUrl);
      redirectStrategy.sendRedirect(request, response, targetUrl);
    }else {
      Object result = buildResponseContent(request);
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write(JSON.toJSONString(result));
    }
  }

  protected Object buildResponseContent(HttpServletRequest request) {
    String message = "session已失效";
    if(isConcurrency()){
      message = message + "，有可能是并发登录导致的";
    }
    return ResultVOUtil.error(ResultEnum.SESSION_INVALID.getCode(), message);
  }

  /**
   * session失效是否是并发导致的
   *
   * @return
   */
  protected boolean isConcurrency() {
    return false;
  }

  public void setCreateNewSession(boolean createNewSession){
    this.createNewSession = createNewSession;
  }

}
