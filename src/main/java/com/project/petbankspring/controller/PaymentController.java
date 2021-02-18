package com.project.petbankspring.controller;


import com.project.petbankspring.controller.dto.PaymentForm;
import com.project.petbankspring.model.Payment;
import com.project.petbankspring.model.User;
import com.project.petbankspring.model.enums.CardCondition;
import com.project.petbankspring.service.CardService;
import com.project.petbankspring.service.PaymentService;
import com.project.petbankspring.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
@Controller
public class PaymentController {

    private PaymentService paymentService;
    private CardService cardService;
    private UserService userService;

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
        Sort sort = pageable.getSort();
        model.addAttribute("currentDirection", Objects.requireNonNull(sort.getOrderFor(sort.iterator().next().getProperty())).isDescending() ? ",desc" : "");
        model.addAttribute("direction", Objects.requireNonNull(sort.getOrderFor(sort.iterator().next().getProperty())).isAscending() ? ",desc" : "");
        model.addAttribute("sort", sort.iterator().next().getProperty());

        return "statements";
    }

    @GetMapping(value = "/payments/{id}")
    public String payments(@PathVariable("id") Long id, Model model) {
        log.info("payments Controller");
        model.addAttribute("cards", cardService.findAllByUserIdAndCardCondition(id, CardCondition.ACTIVE));
        model.addAttribute("paymentForm", new PaymentForm());
        return "payments";
    }

    @PostMapping(value = "payments")
    public String addPayment(@Valid @ModelAttribute("paymentForm") PaymentForm paymentForm, BindingResult error, Model model) {
        User user = userService.getCurrentUser();
        log.info("User id = " + user.getId());

        if (error.hasErrors()) {
            model.addAttribute("cards", cardService.findAllByUserIdAndCardCondition(user.getId(), CardCondition.ACTIVE));
            return "payments";
        }

        Payment payment = paymentService.createPayment(paymentForm);
        if (payment == null) {

            model.addAttribute("cards", cardService.findAllByUserIdAndCardCondition(user.getId(), CardCondition.ACTIVE));
            model.addAttribute("notification", "there no car");
            return "payments";
        }

        log.info("CREATE PAYMENT");
        Long id = paymentService.getIdByCardNumber(paymentForm.getCredit());
        log.info("CardId=" + id);
        return "redirect:/statements/" + id + "?page=0&size=3&sort=id";

    }

    @RequestMapping(value = "/statements/remove", method = RequestMethod.POST)
    public String removePayment(@RequestParam("paymentId") long paymentId, @RequestParam("cardId") long cardId) {
        paymentService.removePayment(paymentId);
        return "redirect:/statements/" + cardId + "?page=0&size=3&sort=id";
    }

    @RequestMapping(value = "/statements/pay", method = RequestMethod.POST)
    public String submitPayment(@RequestParam("payId") long paymentId, @RequestParam("cardId") long cardId, Model model, Pageable pageable) {

        long id = paymentService.findAccountIdByCardId(cardId);
        log.info("account id=" + id);
        if (!paymentService.creditCardBalance(paymentId)) {
            log.info("not balance");
            Page<Payment> statements = paymentService.findPaidPaymentsByAccountId(id, pageable);
            log.info("page statements ="+statements);
            log.info("page paidPayments ="+statements.getContent());
            log.info("page paidPaymentsPages ="+statements.getTotalPages());
            log.info("page currentPage ="+pageable.getPageNumber());
            model.addAttribute("paidPayments", statements.getContent());
            model.addAttribute("paidPaymentsPages", statements.getTotalPages());
            model.addAttribute("currentPage",pageable.getPageNumber());
            model.addAttribute("savedPayments", paymentService.findSavePaymentsByAccountId(id));
            model.addAttribute("card", cardService.findCardByAccountId(id));
            Sort sort = pageable.getSort();
//            model.addAttribute("currentDirection", Objects.requireNonNull(sort.getOrderFor(sort.iterator().next().getProperty())).isDescending() ? ",desc" : "");
//            model.addAttribute("direction", Objects.requireNonNull(sort.getOrderFor(sort.iterator().next().getProperty())).isAscending() ? ",desc" : "");
//            model.addAttribute("sort", sort.iterator().next().getProperty());
            model.addAttribute("notification", "not enough money on the card");
            return "statements";
        }
            paymentService.submitPayment(paymentId);

        return "redirect:/statements/" + cardId + "?page=0&size=3&sort=id";


    }


}
