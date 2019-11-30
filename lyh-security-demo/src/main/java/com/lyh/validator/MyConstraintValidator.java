package com.lyh.validator;

import com.lyh.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/21 1:02
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

  @Autowired
  private HelloService helloService;

  @Override
  public void initialize(MyConstraint constraintAnnotation) {
    System.out.println("my validator init");
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
    helloService.greeting("tom");
    System.out.println(value);
    return false;
  }
}
