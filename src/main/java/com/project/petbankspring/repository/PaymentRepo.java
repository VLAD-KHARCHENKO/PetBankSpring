package com.project.petbankspring.repository;

import com.project.petbankspring.model.Card;
import com.project.petbankspring.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepo extends CrudRepository<Payment, Long> {


}
