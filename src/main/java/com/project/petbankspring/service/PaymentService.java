package com.project.petbankspring.service;

import com.project.petbankspring.model.Payment;
import com.project.petbankspring.repository.PaymentRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class PaymentService {

    private PaymentRepo paymentRepo;

    public List<Payment> findPaidPaymentsByAccountId(long id) {
        return paymentRepo.findAllPaidByAccountId(id);
    }

    public List<Payment> findSavePaymentsByAccountId(long id) {
        return paymentRepo.findAllSaveByAccountId(id);
    }

}
