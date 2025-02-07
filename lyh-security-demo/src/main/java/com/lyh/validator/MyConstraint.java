package com.lyh.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/21 0:59
 * 自定义校验器
 */


@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {

  String message();

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
