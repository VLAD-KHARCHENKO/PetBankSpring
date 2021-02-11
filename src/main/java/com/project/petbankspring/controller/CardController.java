package com.project.petbankspring.controller;


import com.project.petbankspring.controller.dto.CardForm;
import com.project.petbankspring.controller.dto.RegistrationForm;
import com.project.petbankspring.model.User;
import com.project.petbankspring.model.enums.CardCondition;
import com.project.petbankspring.model.enums.CardName;
import com.project.petbankspring.repository.CardRepo;
import com.project.petbankspring.service.CardService;
import com.project.petbankspring.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@AllArgsConstructor
public class CardController {

    private CardService cardService;
private UserService userService;


    @GetMapping(value = "cards/{id}")
    public String cards(@PathVariable("id") Long id, Model model) {
        log.info("cards Controller");
        model.addAttribute("cards", cardService.findUserCards(id));
        model.addAttribute("cardName", CardName.values());
        model.addAttribute("cardForm", new CardForm());
        return "cards";

    }


    @GetMapping(value = "pending-cards")
    public String pendingCards(Model model) {
        log.info("payment-cards Controller");

        model.addAttribute("pendingCards",cardService.findAllPendingCards());

        return "pending-cards";

    }

    @PostMapping(value = "cards")
    public String addCard(@ModelAttribute("cardForm") CardForm cardForm ) {
        log.info("cards Controller");
        cardService.createCard(cardForm);
        Long id=userService.getCurrentUser().getId();
        return "redirect:/cards/"+id;

    }

//    @PostMapping(value = "cards")
//    public String profile(@ModelAttribute("cardForm") CardForm cardForm, Model model) {
//        log.info("Form {}", cardForm);
//       // cardService.createCard(cardForm);
//        return "redirect:/";
//    }


}
