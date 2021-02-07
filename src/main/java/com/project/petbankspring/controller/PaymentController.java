package com.project.petbankspring.controller;


import com.project.petbankspring.service.CardService;
import com.project.petbankspring.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@AllArgsConstructor
@Controller
public class PaymentController {

    private PaymentService paymentService;
    private CardService cardService;


    @GetMapping(value = "/statements/{id}")
    public String statements(@PathVariable("id") Long id, Model model) {
        log.info("statements Controller");
        model.addAttribute("paidPayments", paymentService.findPaidPaymentsByAccountId(id));
        model.addAttribute("savedPayments", paymentService.findSavePaymentsByAccountId(id));
        model.addAttribute("card", cardService.findCardByAccountId(id));
        return "statements";
    }


}
