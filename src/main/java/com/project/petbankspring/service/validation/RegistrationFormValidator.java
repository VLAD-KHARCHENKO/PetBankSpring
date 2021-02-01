//package com.project.petbankspring.service.validation;
//
//import com.project.petbankspring.controller.dto.RegistrationForm;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//@Component(value = "registrationValidator")
//public class RegistrationFormValidator implements Validator {
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return RegistrationForm.class.equals(aClass);
//    }
//
//    @Override
//    public void validate(Object o, Errors errors) {
//        RegistrationForm form = (RegistrationForm) o;
//
//        if (isPasswordNotMatch(form)) {
//            errors.rejectValue("password_confirm", "registration.password.not.match");
//        }
//    }
//
//    private boolean isPasswordNotMatch(RegistrationForm form) {
//        return !form.getPassword().equals(form.getPassword_confirm());
//    }
//}
