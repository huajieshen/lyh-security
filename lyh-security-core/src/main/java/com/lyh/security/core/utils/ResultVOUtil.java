package com.lyh.security.core.utils;

import com.lyh.security.core.vo.ResultVO;

/**
 * @author shenhj
 * @version 1.0
 * Description:
 * @date 2019/12/19 14:16
 */
public class ResultVOUtil {
  public static ResultVO success(Object object) {
    ResultVO resultVO = new ResultVO();
    resultVO.setData(object);
    resultVO.setCode(0);
    resultVO.setMsg("成功");
    return resultVO;
  }

  public static ResultVO success() {
    return success(null);
  }

  public static ResultVO error(Integer code, String msg) {
    ResultVO resultVO = new ResultVO();
    resultVO.setCode(code);
    resultVO.setMsg(msg);
    return resultVO;
  }
}
