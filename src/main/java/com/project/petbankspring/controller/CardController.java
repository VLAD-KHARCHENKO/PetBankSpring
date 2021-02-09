package com.project.petbankspring.controller;


import com.project.petbankspring.controller.dto.CardForm;
import com.project.petbankspring.controller.dto.RegistrationForm;
import com.project.petbankspring.model.enums.CardCondition;
import com.project.petbankspring.model.enums.CardName;
import com.project.petbankspring.repository.CardRepo;
import com.project.petbankspring.service.CardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@AllArgsConstructor
public class CardController {

    private CardService cardService;



    @GetMapping(value = "cards")
    public String cards(Model model) {
        log.info("cards Controller");
        model.addAttribute("cards", cardService.findUserCards());
        model.addAttribute("cardName", CardName.values());
        model.addAttribute("cardCondition", CardCondition.values());
        model.addAttribute("cardForm", new CardForm());
        return "cards";

    }

    @PostMapping(value = "cards")
    public String addCard(@ModelAttribute("cardForm") CardForm cardForm ) {
        log.info("cards Controller");
        cardService.createCard(cardForm);
        return "redirect:/cards";

    }

//    @PostMapping(value = "cards")
//    public String profile(@ModelAttribute("cardForm") CardForm cardForm, Model model) {
//        log.info("Form {}", cardForm);
//       // cardService.createCard(cardForm);
//        return "redirect:/";
//    }


}
