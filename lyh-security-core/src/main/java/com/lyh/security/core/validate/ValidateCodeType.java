package com.lyh.security.core.validate;

import com.lyh.security.core.constant.SecurityConstants;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/12/6 17:59
 */
public enum ValidateCodeType {

  /**
   * 短信验证码
   */
  SMS {
    @Override
    public String getParamNameOnValidate(){
      return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
    }
  },

  /**
   * 图片验证
   */
  IMAGE {
    @Override
    public String getParamNameOnValidate(){
      return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
    }
  };

  /**
   * 校验时从请求中获取的参数的名字
   * @return
   */
  public abstract String getParamNameOnValidate();

}
