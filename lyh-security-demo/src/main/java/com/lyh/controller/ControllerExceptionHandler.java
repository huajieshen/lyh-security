package com.lyh.controller;

import com.lyh.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/25 16:21
 */
@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(UserNotExistException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Map<String, Object> handleUserNotExistException(UserNotExistException ex){
    Map<String, Object> result = new HashMap<>();
    result.put("id", ex.getId());
    result.put("message", ex.getMessage());
    return result;
  }
}
