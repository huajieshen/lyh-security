package com.lyh.code;

import com.lyh.security.core.validate.image.ImageCode;
import com.lyh.security.core.validate.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/5 14:12
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
  @Override
//  public ImageCode createCode(HttpServletRequest request) {
  public ImageCode createCode(ServletWebRequest request) {
    System.out.println("更高级的图形验证码生成代码");
    return null;
  }
}
