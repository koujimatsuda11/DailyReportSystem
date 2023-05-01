package com.techacademy.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techacademy.repository.AuthenticationRepository;

@Component
public class UnusedCodeValidator  implements ConstraintValidator<UnusedCode, String> {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Override
    public void initialize(UnusedCode constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(authenticationRepository == null) {
            return true;
        }
        return !authenticationRepository.existsByCode(value);
    }
}
