package com.project.petbankspring.controller;


import com.project.petbankspring.repository.CardRepo;
import com.project.petbankspring.repository.UserRepo;
import com.project.petbankspring.service.CardService;
import com.project.petbankspring.service.PaymentService;
import com.project.petbankspring.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Slf4j
@Controller
public class InfoPageController {
   

    @GetMapping(value = "/index")
    public String index() {
        log.info("index Controller");
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



    @GetMapping(value = "/")
    public String home() {
        log.info("/ Controller");
        return "index";
    }











}
