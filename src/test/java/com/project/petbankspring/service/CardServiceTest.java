package com.project.petbankspring.service;

import com.project.petbankspring.controller.dto.CardForm;
import com.project.petbankspring.controller.dto.ReplenishmentForm;
import com.project.petbankspring.model.Account;
import com.project.petbankspring.model.Card;
import com.project.petbankspring.model.User;
import com.project.petbankspring.model.enums.CardCondition;
import com.project.petbankspring.model.enums.CardName;
import com.project.petbankspring.model.enums.Role;
import com.project.petbankspring.repository.AccountRepo;
import com.project.petbankspring.repository.CardRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CardServiceTest {

    private Card card;
    private CardForm cardForm;
    private User user;
    private Account account;

    @InjectMocks
    private CardService cardService;

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private CardRepo cardRepo;
    @Mock
    private AccountRepo accountRepo;
    @Mock
    private UserService userService;


    @Before
    public void setUp() {
        cardService = new CardService(cardRepo, accountRepo, userService, paymentService);

        String firstName = "John";
        String lastName = "Johnenko";
        String email = "e@mail";
        String password = "password";
        user = new User(firstName, lastName, email, password, true, Role.CUSTOMER);
        long id = 1L;
        CardName cardName = CardName.UNIVERSAL;
        Long number = 4321L;
        CardCondition condition = CardCondition.ACTIVE;
        account = Account.builder()
                .number("UA26004321").user(user)
                .condition(true).balance(new BigDecimal(0))
                .build();

        card = Card.builder().cardName(cardName)
                .number(number).condition(condition)
                .account(account).build();
        cardForm = new CardForm();
        cardForm.setCardName(CardName.UNIVERSAL);
    }

    @Test
    public void ShouldCreateCard() {
        when(userService.getCurrentUser()).thenReturn(user);
        when(cardRepo.findMaxValueByNumber()).thenReturn(4320L);
        when(accountRepo.save(any(Account.class))).thenAnswer(i -> i.getArguments()[0]);
        when(cardRepo.save(any(Card.class))).thenAnswer(i -> i.getArguments()[0]);

        Card newCard = cardService.createCard(cardForm);

        assertNotNull(newCard);
        assertEquals(card.getAccount(), newCard.getAccount());
        assertEquals(card.getCardName(), newCard.getCardName());
        assertEquals(card.getCondition(), newCard.getCondition());
    }

    @Test
    public void ShouldCreateAccount() {
        when(accountRepo.save(any(Account.class))).thenAnswer(i -> i.getArguments()[0]);

        Account newAccount = cardService.createAccount(user, 4321L);
        assertNotNull(newAccount);
        assertEquals(account.getUser(), newAccount.getUser());
        assertEquals(account.getBalance(), newAccount.getBalance());
        assertEquals(account.getNumber(), newAccount.getNumber());
        assertEquals(account.isCondition(), newAccount.isCondition());
    }

    @Test
    public void ShouldGenerateCardNumber() {
        when(cardRepo.findMaxValueByNumber()).thenReturn(4320L);

        Long cardNumber = cardService.generateCardNumber();
        assertEquals(java.util.Optional.of(4321L), java.util.Optional.of(cardNumber));
    }

    @Test
    public void ShouldBlockedTheCard() {
        when(cardRepo.findById(any())).thenReturn(Optional.ofNullable(card));
        when(cardRepo.save(any(Card.class))).thenAnswer(i -> i.getArguments()[0]);

        Card newCard = cardService.blockedTheCard(1L);
        assertNotNull(newCard);
        assertEquals(CardCondition.BLOCKED, newCard.getCondition());
    }

    @Test
    public void ShouldActivatedTheCard() {
        when(cardRepo.findById(any())).thenReturn(Optional.ofNullable(card));
        when(cardRepo.save(any(Card.class))).thenAnswer(i -> i.getArguments()[0]);

        Card newCard = cardService.activatedTheCard(1L);
        assertNotNull(newCard);
        assertEquals(CardCondition.ACTIVE, newCard.getCondition());
    }

    @Test
    public void pendedTheCard() {
        when(cardRepo.findById(any())).thenReturn(Optional.ofNullable(card));
        when(cardRepo.save(any(Card.class))).thenAnswer(i -> i.getArguments()[0]);

        Card newCard = cardService.pendedTheCard(1L);
        assertNotNull(newCard);
        assertEquals(CardCondition.PENDING, newCard.getCondition());
    }

    @Test
    public void ShouldReplenishmentCard() {
        ReplenishmentForm replenishmentForm = new ReplenishmentForm();
        replenishmentForm.setAmount(new BigDecimal(100));
        replenishmentForm.setCardNumber(4321L);

        when(cardRepo.findByNumber(anyLong())).thenReturn(Optional.ofNullable(card));
        when(cardRepo.save(any(Card.class))).thenAnswer(i -> i.getArguments()[0]);

        Card newCard = cardService.replenishmentCard(replenishmentForm);

        assertNotNull(newCard);
        assertEquals(new BigDecimal(100), newCard.getAccount().getBalance());
    }

}
