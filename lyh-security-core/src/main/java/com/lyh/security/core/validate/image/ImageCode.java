package com.lyh.security.core.validate.image;

import com.lyh.security.core.validate.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/2 23:09
 */
public class ImageCode extends ValidateCode {

  //    图片信息
  private BufferedImage image;

  public ImageCode(BufferedImage image, String code, int expireIn) {
    super(code, expireIn);
    this.image = image;
  }
  public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
    super(code, expireTime);
    this.image = image;
  }

  public BufferedImage getImage() {
    return image;
  }

  public void setImage(BufferedImage image) {
    this.image = image;
  }

}
