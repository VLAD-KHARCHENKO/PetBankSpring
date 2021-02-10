package com.project.petbankspring.service;

import com.project.petbankspring.controller.dto.CardForm;
import com.project.petbankspring.model.Account;
import com.project.petbankspring.model.Card;
import com.project.petbankspring.model.User;
import com.project.petbankspring.model.enums.CardCondition;
import com.project.petbankspring.repository.AccountRepo;
import com.project.petbankspring.repository.CardRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CardService {

    private CardRepo cardRepo;
    private AccountRepo accountRepo;
    private UserService userService;

    public List<Card> findUserCards(long id) {
        return cardRepo.findAllByUserId(userService.getUserById(id).getId());
    }


    public Card createCard(CardForm cardForm) {
        log.info("Create card");
        Long cardNumber = generateCardNumber();
        return cardRepo.save(Card.builder()
                .cardName(cardForm.getCardName())
                .number(cardNumber)
                .condition(CardCondition.ACTIVE)
                .account(createAccount(userService.getCurrentUser(), cardNumber))
                .build());

    }
    public List<Card> findAllPendingCards(){
        log.info("Pending-cards");
        return cardRepo.findAllPendingCards();
    }

    public Account createAccount(User user, Long cardNumber) {
        return accountRepo.save(Account.builder()
                .number("UA2600" + cardNumber)
                .user(user)
                .condition(true)
                .balance(new BigDecimal(0))
                .build());
    }

    public Long generateCardNumber() {
        Long newNumber = (cardRepo.findMaxValueByNumber());
        return ++newNumber;
    }

    public Card findCardByAccountId(Long id) {
        return cardRepo.findByAccountId(id);
    }

}
