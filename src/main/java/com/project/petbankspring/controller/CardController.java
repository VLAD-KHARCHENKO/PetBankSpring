package com.project.petbankspring.controller;


import com.project.petbankspring.model.enums.CardCondition;
import com.project.petbankspring.model.enums.CardName;
import com.project.petbankspring.repository.CardRepo;
import com.project.petbankspring.service.CardService;
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
public class CardController {

    @Autowired
    private CardRepo cardRepo;

    @Autowired
    private CardService cardService;



    @GetMapping(value = "cards")
    public String cards( Model model) {
        log.info("cards Controller");
        model.addAttribute("cards",cardService.findUserCards());
        model.addAttribute("cardName", CardName.values());
        model.addAttribute("cardCondition", CardCondition.values());
        return "cards";

    }

    @GetMapping(value = "createCard")
    public String cards(@RequestParam(value = "cardName")CardName cardName, Model model) {
        log.info("cards Controller");
        cardService.createCard(cardName);
        return "redirect:/cards";

    }

//    @PostMapping(value = "cards")
//    public String profile(@ModelAttribute("cardForm") CardForm cardForm, Model model) {
//        log.info("Form {}", cardForm);
//       // cardService.createCard(cardForm);
//        return "redirect:/";
//    }


}
