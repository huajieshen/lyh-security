package com.lyh.security.browser.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author shenhj
 * @date 2019/12/19 21:28
 * @version 1.0
 * Description:
 * 如果设置的session并发策略为一个账户第二次登陆会将第一次给踢下来
 * 则第一次登陆的用户再访问我们的项目时会进入到该类
 * event里封装了request、response信息
 */
public class LyhExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {
  /**
   * @param invalidSessionUrl
   */
  public LyhExpiredSessionStrategy(String invalidSessionUrl) {
    super(invalidSessionUrl);
  }

  @Override
  public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
//    event.getResponse().setContentType("application/json;charset=UTF-8");
//    event.getResponse().getWriter().write("并发登录！");
    onSessionInvalid(event.getRequest(), event.getResponse());
  }

  @Override
  protected boolean isConcurrency() {
    return true;
  }
}
