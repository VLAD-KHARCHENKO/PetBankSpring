package com.project.petbankspring.controller;


import com.project.petbankspring.repository.UserRepo;
import com.project.petbankspring.service.UserService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class InfoPageController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/index")
    public String index() {
        log.("index Controller");
        return "index";
    }

    @GetMapping(value = "/admin")
    public String admin() {
        log.info("admin Controller");
        return "admin";
    }

    @GetMapping(value = "/user")
    public String user() {
        log.info("user Controller");
        return "user";
    }

    @GetMapping(value = "/cards")
    public String cards() {
        log.info("cards Controller");
        return "cards";
    }

    @GetMapping(value = "/")
    public String home() {
        log.info("/ Controller");
        return "index";
    }

    @GetMapping(value = "/accounts")
    public String accounts() {
        log.info("accounts Controller");
        return "accounts";
    }

    @GetMapping(value = "/statements")
    public String statements() {
        log.info("statements Controller");
        return "statements";
    }


    @GetMapping(value = "/users")
    public String users(Model model) {
        log.info("users Controller");
        model.addAttribute("users",userRepo.findAll());

        return "users";
    }

    @GetMapping(value = "/payments")
    public String payments() {
        log.info("payments Controller");
        return "payments";
    }


}
