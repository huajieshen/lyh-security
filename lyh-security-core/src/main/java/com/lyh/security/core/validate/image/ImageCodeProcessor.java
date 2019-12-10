package com.lyh.security.core.validate.image;

import com.lyh.security.core.validate.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/6 20:58
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

  /**
   * 发送图形验证码，将其写到响应中
   * @param request
   * @param imageCode
   * @throws Exception
   */
  @Override
  protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
    ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
  }


}
