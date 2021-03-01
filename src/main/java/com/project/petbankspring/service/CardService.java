package com.project.petbankspring.service;

import com.project.petbankspring.controller.dto.CardForm;
import com.project.petbankspring.controller.dto.ReplenishmentForm;
import com.project.petbankspring.exception.EntityNotFoundException;
import com.project.petbankspring.model.Account;
import com.project.petbankspring.model.Card;
import com.project.petbankspring.model.User;
import com.project.petbankspring.model.enums.CardCondition;
import com.project.petbankspring.repository.AccountRepo;
import com.project.petbankspring.repository.CardRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepo cardRepo;
    private final AccountRepo accountRepo;
    private final UserService userService;
    private final PaymentService paymentService;

    public List<Card> findUserCards(long id, Pageable pageable) {
        return cardRepo.findAllByUserId(userService.getUserById(id).getId(), pageable);
    }

    public List<Card> findAllByUserIdAndCardCondition(long id, CardCondition cardCondition) {
        return cardRepo.findAllByUserIdAndCardCondition(id, cardCondition);
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

    public List<Card> findAllPendingCards() {
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

    public Card blockedTheCard(long cardId) {
        Card card = cardRepo.findById(cardId).orElseThrow(() -> new EntityNotFoundException("Card not found"));
        card.setCondition(CardCondition.BLOCKED);
        return cardRepo.save(card);
    }

    public Card activatedTheCard(long cardId) {
        Card card = cardRepo.findById(cardId).orElseThrow(() -> new EntityNotFoundException("Card not found"));
        card.setCondition(CardCondition.ACTIVE);
      return   cardRepo.save(card);
    }

    public Card pendedTheCard(long cardId) {
        Card card = cardRepo.findById(cardId).orElseThrow(() -> new EntityNotFoundException("Card not found"));
        card.setCondition(CardCondition.PENDING);
      return cardRepo.save(card);
    }

    public Card findCardByAccountId(Long id) {
        return cardRepo.findByAccountId(id);
    }

    public Card replenishmentCard(ReplenishmentForm replenishmentForm) {
        Card card = cardRepo.findByNumber(replenishmentForm.getCardNumber()).orElseThrow(() -> new EntityNotFoundException("Card not found"));
        Account account = paymentService.changeBalance(card.getAccount(), replenishmentForm.getAmount());
        card.setAccount(account);
      return cardRepo.save(card);
    }

}

