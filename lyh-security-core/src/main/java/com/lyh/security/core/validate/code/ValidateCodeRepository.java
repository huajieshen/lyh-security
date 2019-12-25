package com.lyh.security.core.validate.code;

import com.lyh.security.core.validate.ValidateCode;
import com.lyh.security.core.validate.ValidateCodeType;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author shenhj
 * @date 2019/12/24 16:17
 * @version 1.0
 * Description: 保存、获取、移除验证码的模版接口
 */
public interface ValidateCodeRepository {
  /**
   * 保存验证码
   * @param request
   * @param code
   * @param validateCodeType
   */
  void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

  /**
   * 获取验证码
   * @param request
   * @param validateCodeType
   * @return
   */
  ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);

  /**
   * 移除验证码
   * @param request
   * @param validateCodeType
   */
  void remove(ServletWebRequest request, ValidateCodeType validateCodeType);


}
