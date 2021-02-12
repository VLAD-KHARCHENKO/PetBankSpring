package com.project.petbankspring.controller;


import com.project.petbankspring.controller.dto.PaymentForm;
import com.project.petbankspring.model.Payment;
import com.project.petbankspring.model.enums.CardCondition;
import com.project.petbankspring.service.CardService;
import com.project.petbankspring.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@Controller
public class PaymentController {

    private PaymentService paymentService;
    private CardService cardService;


    @GetMapping(value = "/statements/{id}")
    public String statements(@PathVariable("id") Long id, Model model, Pageable pageable) {
        log.info("statements Controller");
        Page<Payment> statements = paymentService.findPaidPaymentsByAccountId(id, pageable);
        model.addAttribute("paidPayments", statements.getContent());
       model.addAttribute("paidPaymentsPages", statements.getTotalPages());
        model.addAttribute("currentPage", pageable.getPageNumber());

        //    model.addAttribute("paidPayments", paymentService.findPaidPaymentsByAccountId(id));


       model.addAttribute("savedPayments", paymentService.findSavePaymentsByAccountId(id));
        model.addAttribute("card", cardService.findCardByAccountId(id));

        return "statements";
    }

    @GetMapping(value = "/payments/{id}")
    public String payments(@PathVariable("id") Long id, Model model) {
        log.info("payments Controller");
        model.addAttribute("cards", cardService. findAllByUserIdAndCardCondition(id, CardCondition.ACTIVE));
        model.addAttribute("paymentForm", new PaymentForm());
        return "payments";
    }

    @PostMapping(value = "payments")
    public String addPayment(@ModelAttribute("paymentForm") PaymentForm paymentForm) {
        paymentService.createPayment(paymentForm);
        log.info("CREATE PAYMENT");
        Long id = paymentService.getIdByCardNumber(paymentForm.getCredit());
        log.info("CardId=" + id);
        return "redirect:/statements/" + id;

    }

    @RequestMapping(value ="/statements/remove", method = RequestMethod.POST)
    public String removePayment(@RequestParam("paymentId") long paymentId,@RequestParam("cardId") long cardId){
        paymentService.removePayment(paymentId);
        return "redirect:/statements/"+cardId+"?page=0&size=3";
    }

    @RequestMapping(value ="/statements/pay", method = RequestMethod.POST)
    public String submitPayment(@RequestParam("payId") long paymentId, @RequestParam("cardId") long cardId){
        paymentService.submitPayment(paymentId);
        return "redirect:/statements/"+cardId+"?page=0&size=3";
    }


}
