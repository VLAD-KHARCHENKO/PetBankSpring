package com.project.petbankspring.repository;

import com.project.petbankspring.model.Card;
import com.project.petbankspring.model.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentRepo extends CrudRepository<Payment, Long> {

    @Query("select payment from Payment payment where payment.status = 'PAID' and (payment.debit.id= :accountId or payment.credit.id = :accountId)" )
    List<Payment> findAllPaidByAccountId(long accountId);

    @Query("select payment from Payment payment where payment.status = 'SAVE' and (payment.debit.id = :accountId or payment.credit.id = :accountId)" )
    List<Payment> findAllSaveByAccountId(long accountId);


}
