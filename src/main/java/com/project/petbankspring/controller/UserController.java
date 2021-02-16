package com.project.petbankspring.controller;


import com.project.petbankspring.controller.dto.ProfileForm;
import com.project.petbankspring.model.User;
import com.project.petbankspring.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Objects;

@Slf4j
@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;



    @GetMapping(value = "profile")
    public String profile(@RequestParam(value = "id") Long id, Model model) {
        log.info("Get profile Page");
        model.addAttribute("profileUser", userService.getUserById(id));
//        model.addAttribute("currentUser", userService.getCurrentUser());
        log.info("addAttribute currentUser" + userService.getCurrentUser());
        model.addAttribute("profileForm", userService.getProfileForm(id));
        log.info("addAttribute profileForm" + userService.getProfileForm(id));
        return "profile";
    }

    @PostMapping(value = "profile")
    public String profile(@ModelAttribute("profileForm") ProfileForm profileForm, Model model) {
        log.info("Form {}", profileForm);
        userService.updateUser(profileForm);
        return "redirect:/";
    }

    @GetMapping(value = "users")
    public String users(Model model, Pageable pageable) {
        log.info("users Controller");

        Page<User> users = userService.findAll(pageable);

        model.addAttribute("users", users.getContent());
        model.addAttribute("usersPages", users.getTotalPages());
        model.addAttribute("currentPage", pageable.getPageNumber());
        Sort sort = pageable.getSort();
        model.addAttribute("currentDirection", Objects.requireNonNull(sort.getOrderFor(sort.iterator().next().getProperty())).isDescending()? ",desc" : "");
        model.addAttribute("direction", Objects.requireNonNull(sort.getOrderFor(sort.iterator().next().getProperty())).isAscending()? ",desc" : "");
        model.addAttribute("sort", sort.iterator().next().getProperty());

        return "users";
    }
}
