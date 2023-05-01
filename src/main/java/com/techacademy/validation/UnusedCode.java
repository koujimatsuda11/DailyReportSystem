package com.techacademy.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UnusedCodeValidator.class})
public @interface UnusedCode {
  String message() default "社員codeは既に使われています";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}