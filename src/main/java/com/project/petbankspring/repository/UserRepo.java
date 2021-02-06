package com.project.petbankspring.repository;

import com.project.petbankspring.model.Card;
import com.project.petbankspring.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepo extends PagingAndSortingRepository<User, Long> {

    boolean existsByLogin(String login);

    User findByLogin(String login);

    Page<User> findAll(Pageable pageable);

//   List<User> findAll();

}
