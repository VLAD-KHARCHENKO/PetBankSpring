package com.project.petbankspring.controller;


import com.project.petbankspring.controller.dto.CardForm;
import com.project.petbankspring.model.enums.CardName;
import com.project.petbankspring.service.CardService;
import com.project.petbankspring.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

        model.addAttribute("pendingCards", cardService.findAllPendingCards());

        return "pending-cards";

    }

    @PostMapping(value = "cards")
    public String addCard(@ModelAttribute("cardForm") CardForm cardForm) {
        log.info("cards Controller");
        cardService.createCard(cardForm);
        Long id = userService.getCurrentUser().getId();
        return "redirect:/cards/" + id;

    }

    @RequestMapping(value ="/cards/blocked", method = RequestMethod.POST)
    public String blockedCard(@RequestParam("cardId") long cardId, @RequestParam("userId") long userId){
       cardService.blockedTheCard(cardId);
        return "redirect:/cards/"+userId;
    }

    @RequestMapping(value ="/pending-cards/activated", method = RequestMethod.POST)
    public String activatedPendingCard(@RequestParam("cardId") long cardId){
        cardService.activatedTheCard(cardId);
        return "redirect:/pending-cards/";
    }

    @RequestMapping(value ="/cards/activated", method = RequestMethod.POST)
    public String activatedCard(@RequestParam("cardId") long cardId, @RequestParam("userId") long userId){
        cardService.activatedTheCard(cardId);
        return "redirect:/cards/"+userId;
    }

    @RequestMapping(value ="/cards/pending", method = RequestMethod.POST)
    public String pendedCard(@RequestParam("cardId") long cardId, @RequestParam("userId") long userId){
        cardService.pendedTheCard(cardId);
        return "redirect:/cards/"+userId;
    }

}
