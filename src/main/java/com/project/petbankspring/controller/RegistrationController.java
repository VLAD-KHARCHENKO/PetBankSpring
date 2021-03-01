package com.project.petbankspring.controller;

import com.project.petbankspring.controller.dto.RegistrationForm;
import com.project.petbankspring.exception.UserExistException;
import com.project.petbankspring.model.enums.Role;
import com.project.petbankspring.service.UserService;
import com.project.petbankspring.service.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    @Qualifier("registrationValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @GetMapping
    public String getRegistrationPage(Model model) {
        log.info("Get Registration Page");
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registration";
    }

    @PostMapping
    public String registerUser(@Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm, BindingResult error) {
        log.info("Form {}", registrationForm);
        if (error.hasErrors()) {
            return "registration";
        }

        try {
            userService.registerUser(registrationForm, Role.CUSTOMER);
        } catch (UserExistException e) {
            log.error(e.getMessage());
            error.rejectValue("login", "registration.login.exist");
            return "registration";
        }
        securityService.autoLogin(registrationForm.getLogin(), registrationForm.getPassword());
        return "redirect:/";
    }

}
