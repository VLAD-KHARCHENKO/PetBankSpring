package com.project.petbankspring.controller;

import com.project.petbankspring.controller.dto.LoginForm;
import com.project.petbankspring.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String login(Model model) {
        log.info("Login Controller");
        model.addAttribute("command", new LoginForm());

        return "login";
    }

}
