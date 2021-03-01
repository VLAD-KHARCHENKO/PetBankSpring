package com.project.petbankspring.service.validation;

import com.project.petbankspring.controller.dto.RegistrationForm;
import com.project.petbankspring.service.validation.annotation.PasswordMatcher;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatcher, RegistrationForm> {

    @Override
    public boolean isValid(RegistrationForm form, ConstraintValidatorContext constraintValidatorContext) {
        if (form.getPassword() == null || form.getPassword_confirm() == null) {
            return true;
        }
        return form.getPassword().equals(form.getPassword_confirm());
    }

}
