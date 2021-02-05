package com.project.petbankspring.repository;

import com.project.petbankspring.model.Account;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepo extends CrudRepository<Account, Long> {


}
