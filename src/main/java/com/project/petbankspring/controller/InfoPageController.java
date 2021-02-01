package com.project.petbankspring.controller;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoPageController {
//    @Autowired
//    private FeedbackRepo repository;

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping(value = "/user")
    public String user() {
        return "user";
    }

    @GetMapping(value = "/cards")
    public String cards() {
        return "cards";
    }

    @GetMapping(value = "/")
    public String home() {
//        model.addAttribute("feedback", repository.findAll());
        return "index";
    }

    @GetMapping(value = "/accounts")
    public String accounts() {
        return "accounts";
    }

    @GetMapping(value = "/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping(value = "/users")
    public String users() {
        return "users";
    }

    @GetMapping(value = "/payments")
    public String payments() {
        return "payments";
    }

//    @GetMapping(value = "team-member-profile")
//    public String member() {
//        return "team-member-profile";
//    }
//
//    @GetMapping(value = "template")
//    public String template() {
//        return "template";
//    }
}
