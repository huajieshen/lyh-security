package com.lyh.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/27 22:53
 */
@Component
public class MockQueue {

  private Logger logger = LoggerFactory.getLogger(getClass());

  private String placeOrder;
  private String completeOrder;

  public String getPlaceOrder() {
    return placeOrder;
  }

  public void setPlaceOrder(String placeOrder)throws Exception {
   new Thread(() -> {
     logger.info("接到下单请求" + placeOrder);
     try {
       Thread.sleep(1000);
     } catch (InterruptedException e) {
       e.printStackTrace();
     }
     this.completeOrder = placeOrder;
     logger.info("下单请求处理完毕" + placeOrder);
   }).start();
  }

  public String getCompleteOrder() {
    return completeOrder;
  }

  public void setCompleteOrder(String completeOrder) {
    this.completeOrder = completeOrder;
  }
}
