package com.lyh.async;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/27 23:02
 */
@Component
public class DeferredResultHolder {

  private Map<String, DeferredResult<String>> map = new HashMap<>();

  public Map<String, DeferredResult<String>> getMap() {
    return map;
  }

  public void setMap(Map<String, DeferredResult<String>> map) {
    this.map = map;
  }
}
