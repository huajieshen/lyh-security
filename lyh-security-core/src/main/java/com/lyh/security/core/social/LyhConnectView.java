package com.lyh.security.core.social;

import com.alibaba.fastjson.JSON;
import com.lyh.security.core.utils.ResultVOUtil;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author shenhj
 * @version 1.0
 * Description:
 * @date 2019/12/19 14:12
 */
public class LyhConnectView extends AbstractView {
  @Override
  protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {
    response.setContentType("application/json;charset=UTF-8");
    //如果map（Model对象）里有Connection对象，则表示绑定，否则表示解绑

    if (map.get("connections") == null) {
      response.getWriter().write(JSON.toJSONString(ResultVOUtil.success("解绑成功")));
    } else {
      response.getWriter().write(JSON.toJSONString(ResultVOUtil.success("绑定成功")));
    }
  }
}
