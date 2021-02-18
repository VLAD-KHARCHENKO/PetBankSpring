package com.project.petbankspring.service.validation;

import com.project.petbankspring.controller.dto.RegistrationForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component(value = "registrationValidator")
public class RegistrationFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationForm form = (RegistrationForm) o;

        if (isPasswordNotMatch(form)) {
            errors.rejectValue("password_confirm", "registration.password.not.match");
        }
        if (isFirstNameNotMatch(form)) {
            errors.rejectValue("firstName", "firstName");
        }
        if (isLastNameNotMatch(form)) {
            errors.rejectValue("lastName", "lastName");
        }
        if (isLoginNotMatch(form)) {
            errors.rejectValue("login", "loginError");
        }
    }

    private boolean isPasswordNotMatch(RegistrationForm form) {
        return !form.getPassword().equals(form.getPassword_confirm());
    }
    private boolean isLoginNotMatch(RegistrationForm form) {
        return !(form.getLogin()==null);
    }

    private boolean isFirstNameNotMatch(RegistrationForm form) {
        int lengthName = form.getFirstName().length();
        return !(lengthName > 3 && lengthName < 30);
    }
    private boolean isLastNameNotMatch(RegistrationForm form) {
        int lengthName = form.getLastName().length();
        return !(lengthName > 3 && lengthName < 30);
    }
}
