package com.project.petbankspring.service;

import com.project.petbankspring.controller.dto.PaymentForm;
import com.project.petbankspring.exception.EntityNotFoundException;
import com.project.petbankspring.model.Account;
import com.project.petbankspring.model.Card;
import com.project.petbankspring.model.Payment;
import com.project.petbankspring.model.enums.Status;
import com.project.petbankspring.repository.AccountRepo;
import com.project.petbankspring.repository.CardRepo;
import com.project.petbankspring.repository.PaymentRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.method.P;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class PaymentService {

    private PaymentRepo paymentRepo;
    private AccountRepo accountRepo;
    private CardRepo cardRepo;

    public Page<Payment> findPaidPaymentsByAccountId(long id, Pageable pageable) {
        return paymentRepo.findAllPaidByAccountId(id, pageable);
    }

    public List<Payment> findSavePaymentsByAccountId(long id) {
        return paymentRepo.findAllSaveByAccountId(id);
    }

    public Payment createPayment(PaymentForm paymentForm) {
        if(!isDebitCardExist(paymentForm.getDebit())){
            return null;
        }

        return paymentRepo.save(Payment.builder()
                        .date(LocalDateTime.now())
                        .debit(getAccountByCardNumber(paymentForm.getDebit()))
                        .credit(getAccountByCardNumber(paymentForm.getCredit()))
                        .description(paymentForm.getDescription())
                        .amount(paymentForm.getAmount())
                        .status(Status.SAVE)
                        .build());

    }

    private boolean isDebitCardExist(String debit) {
        return cardRepo.existsByNumber(Long.parseLong(debit));

    }


    public Long getIdByCardNumber(String number) {
        Long cardNumber = Long.parseLong(number);
        Card card = cardRepo.findByNumber(cardNumber);
        return card.getId();
    }

    public Account getAccountByCardNumber(String number) {
        Long cardNumber = Long.parseLong(number);
        Card card = cardRepo.findByNumber(cardNumber);
        return card.getAccount();
    }

    public void removePayment(long paymentId) {
        paymentRepo.deleteById(paymentId);
    }

    @Transactional
    public void submitPayment(long paymentId) {
        Payment payment = paymentRepo.findById(paymentId).get();
        payment.setCredit(changeBalance(payment.getCredit(), payment.getAmount().negate()));
        payment.setDebit(changeBalance(payment.getDebit(), payment.getAmount()));
        payment.setDate(LocalDateTime.now());
        payment.setStatus(Status.PAID);
        paymentRepo.save(payment);
    }

    public Account changeBalance(Account account, BigDecimal amount) {

        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);
        return account;
    }

    public boolean creditCardBalance(Long id){
        Payment payment = paymentRepo.findById(id).get();
        return payment.getCredit().getBalance().compareTo(payment.getAmount()) >= 0;
    }

    public long findAccountIdByCardId(long cardId) {
        log.info("findAccountIdByCardId");
        return cardRepo.findAccountIdByCardId(cardId);
    }
}
