package com.project.petbankspring.controller;

//import com.example.repairagencyspringboot.controller.dto.ProfileForm;
//import com.example.repairagencyspringboot.controller.dto.UserProfileForm;
//import com.example.repairagencyspringboot.model.User;
//import com.example.repairagencyspringboot.model.enums.Status;
//import com.example.repairagencyspringboot.repository.OrderRepo;
//import com.example.repairagencyspringboot.repository.UserRepo;
//import com.example.repairagencyspringboot.service.UserService;
import com.project.petbankspring.controller.dto.ProfileForm;
import com.project.petbankspring.repository.UserRepo;
import com.project.petbankspring.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


}
