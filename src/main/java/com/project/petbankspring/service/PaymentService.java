package com.project.petbankspring.service;

import com.project.petbankspring.controller.dto.PaymentForm;
import com.project.petbankspring.model.Account;
import com.project.petbankspring.model.Payment;
import com.project.petbankspring.model.enums.Status;
import com.project.petbankspring.repository.AccountRepo;
import com.project.petbankspring.repository.CardRepo;
import com.project.petbankspring.repository.PaymentRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class PaymentService {

    private PaymentRepo paymentRepo;
    private CardRepo cardRepo;
    private AccountRepo accountRepo;

    public List<Payment> findPaidPaymentsByAccountId(long id) {
        return paymentRepo.findAllPaidByAccountId(id);
    }

    public List<Payment> findSavePaymentsByAccountId(long id) {
        return paymentRepo.findAllSaveByAccountId(id);
    }

    public void createPayment(PaymentForm paymentForm) {

        paymentRepo.save(Payment.builder()
                .date(LocalDateTime.now())
                .debit(accountRepo.findById(1L).get())
                .credit(accountRepo.findById(2L).get())
                .description(paymentForm.getDescription())
                .amount(paymentForm.getAmount())
                .status(Status.SAVE)

                .build());
    }
}
