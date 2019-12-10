package com.lyh.security.core.properties;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/4 21:19
 */
public class ImageCodeProperties extends SmsCodeProperties{

  public ImageCodeProperties(){
    setLength(4);
  }

  private int width = 67;
  private int height = 23;

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

}
