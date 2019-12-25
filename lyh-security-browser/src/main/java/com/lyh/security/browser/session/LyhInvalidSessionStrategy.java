package com.lyh.security.browser.session;

import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shenhj
 * @date 2019/12/19 23:21
 * @version 1.0
 * Description: session超时的处理策略
 */
public class LyhInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {
  /**
   * @param invalidSessionUrl
   */
  public LyhInvalidSessionStrategy(String invalidSessionUrl) {
    super(invalidSessionUrl);
  }

  @Override
  public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    onSessionInvalid(request, response);
  }
}
