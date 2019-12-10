package com.lyh.security.core.validate.image;

import com.lyh.security.core.properties.SecurityProperties;
import com.lyh.security.core.validate.ValidateCodeGenerator;
import com.lyh.security.core.validate.image.ImageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/5 12:36
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {

  @Autowired
  private SecurityProperties securityProperties;

  @Override
//  public ImageCode createCode(HttpServletRequest request) {
  public ImageCode createCode(ServletWebRequest request) {
    // 图片的宽高（像素）
//    int width = ServletRequestUtils.getIntParameter(request, "width", securityProperties.getCodeProperties().getImage().getWidth());
//    int height = ServletRequestUtils.getIntParameter(request, "height", securityProperties.getCodeProperties().getImage().getHeight());

    int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", securityProperties.getCodeProperties().getImage().getWidth());
    int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height", securityProperties.getCodeProperties().getImage().getHeight());
    int length = securityProperties.getCodeProperties().getImage().getLength();
    int expireTime = securityProperties.getCodeProperties().getImage().getExpireIn();


    // 生成图片对象
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    Graphics g = image.getGraphics();
    // 生成随机条纹干扰
    Random random = new Random();
    g.setColor(getRandColor(200, 250));
    g.fillRect(0, 0, width, height);
    g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
    g.setColor(getRandColor(160, 200));
    for (int i = 0; i < 155; i++) {
      int x = random.nextInt(width);
      int y = random.nextInt(height);
      int xl = random.nextInt(12);
      int yl = random.nextInt(12);
      g.drawLine(x, y, xl, yl);
    }

    // 生成四位随机数
    String sRand = "";
//    for (int i = 0; i < 4; i++) {
    for (int i = 0; i < length; i++) {
      String rand = String.valueOf(random.nextInt(10));
      sRand += rand;
      g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
      g.drawString(rand, 13 * i + 6, 16);
    }
    g.dispose();
    // 60秒有效
//    return new ImageCode(image, sRand, 60);
    return new ImageCode(image, sRand, expireTime);
  }

  /**
   * 生成随机背景条纹
   *
   * @param fc
   * @param bc
   * @return
   */
  private Color getRandColor(int fc, int bc) {
    Random random = new Random();
    if (fc > 255) {
      fc = 255;
    }
    if (bc > 255) {
      bc = 255;
    }
    int r = fc + random.nextInt(bc - fc);
    int g = fc + random.nextInt(bc - fc);
    int b = fc + random.nextInt(bc - fc);
    return new Color(r, g, b);
  }

  public SecurityProperties getSecurityProperties() {
    return securityProperties;
  }

  public void setSecurityProperties(SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }
}

