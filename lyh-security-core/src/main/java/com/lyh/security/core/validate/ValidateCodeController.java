package com.lyh.security.core.validate;

import com.lyh.security.core.constant.SecurityConstants;
import com.lyh.security.core.properties.SecurityProperties;
import com.lyh.security.core.validate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.method.P;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Random;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/2 23:11
 */
@RestController
public class ValidateCodeController {

  @Autowired
  private ValidateCodeProcessorHolder validateCodeProcessorHolder;

  @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
  public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
          throws Exception{
    validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
  }

  //=====================================================

//  @Autowired
//  private Map<String, ValidateCodeProcessor> validateCodeProcessors;
//
//  @GetMapping("/code/{type}")
//  public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
//          throws Exception{
//    validateCodeProcessors.get(type + "CodeProcessor").create(new ServletWebRequest(request, response));
//  }

  //======================================================

  //  private static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
//  public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

//  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

//  @Autowired
//  private ValidateCodeGenerator imageCodeGenerator;
//
//  @Autowired
//  private ValidateCodeGenerator smsCodeGenerator;
//
//  @Autowired
//  private SmsCodeSender smsCodeSender;


//  //图片验证
//  @GetMapping("/code/image")
//  public void createCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
//    // 根据随机数生成图片
//    ImageCode imageCode = (ImageCode)imageCodeGenerator.createCode(new ServletWebRequest(request));
//    // 将随机数存到session中,
//    //第一个参数是把请求传进去，然后sessionStrategy会从请求里拿session
//    sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
//    // 将生成的图片写到接口的响应中
//    ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
//
//  }
//
//  // 手机验证
//  @GetMapping("/code/sms")
//  public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
//    // 根据随机数生成验证码
//    ValidateCode smsCode =  smsCodeGenerator.createCode(new ServletWebRequest(request));
//    // 将随机数存到session中,
//    //第一个参数是把请求传进去，然后sessionStrategy会从请求里拿session
//    sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);
//
//    String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
//    // 通过短信服务商发送短信出去
//    smsCodeSender.send(mobile, smsCode.getCode());
//
//  }




}
