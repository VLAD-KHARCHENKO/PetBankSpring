package com.project.petbankspring.repository;

import com.project.petbankspring.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PaymentRepo extends PagingAndSortingRepository<Payment, Long> {

    @Query("select payment from Payment payment where payment.status = 'PAID' and (payment.debit.id= :accountId or payment.credit.id = :accountId)" )
    Page<Payment> findAllPaidByAccountId(long accountId, Pageable pageable);

    @Query("select payment from Payment payment where payment.status = 'SAVE' and payment.credit.id = :accountId" )
    List<Payment> findAllSaveByAccountId(long accountId);


}
