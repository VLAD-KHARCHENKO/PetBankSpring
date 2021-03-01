package com.project.petbankspring.service;

import com.project.petbankspring.controller.dto.ProfileForm;
import com.project.petbankspring.controller.dto.RegistrationForm;
import com.project.petbankspring.exception.UserExistException;
import com.project.petbankspring.model.User;
import com.project.petbankspring.model.enums.Role;
import com.project.petbankspring.repository.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    private User user;
    private RegistrationForm form;
    private ProfileForm profileForm;

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        userService = new UserService(userRepo, passwordEncoder);
        long id = 1L;
        String firstName = "John";
        String lastName = "Johnenko";
        String email = "e@mail";
        String password = "password";
        String hashedPass = passwordEncoder.encode(password);
        user = new User(firstName, lastName, email, password, true, Role.CUSTOMER);
        form = new RegistrationForm();
        form.setFirstName(firstName);
        form.setLastName(lastName);
        form.setLogin(email);
        form.setPassword(password);
        form.setPassword_confirm(password);
        profileForm = ProfileForm.builder().userId("1").firstName("NewJohn")
                .lastName("NewJohnenko").login("e@mail").password("NewPassword")
                .condition(false).role(Role.ADMIN).build();
    }

    @Test
    public void validateUserSuccess() {
        String login = "e@mail";
        String password = "password";
        when(userRepo.findByLogin(any())).thenReturn(user);

        Optional<User> user = userService.validateUser(login, password);
        assertTrue(user.isPresent());
    }

    @Test
    public void validateUserError() {
        String login = "login";
        String wrongPassword = "wrongPassword";
        when(userRepo.findByLogin(any())).thenReturn(user);
        Optional<User> user = userService.validateUser(login, wrongPassword);
        assertFalse(user.isPresent());
    }

    @Test(expected = UserExistException.class)
    public void registerUserError() {
        when(userRepo.existsByLogin(any())).thenReturn(true);
        userService.registerUser(form, Role.CUSTOMER);
    }

    @Test
    public void registerUserSuccess() {

        when(userRepo.existsByLogin(any())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("EncodedPassword");
        when(userRepo.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        User user = userService.registerUser(form, Role.CUSTOMER);
        assertNotNull(user);
        assertEquals(form.getFirstName(), user.getFirstName());
        assertEquals(form.getLastName(), user.getLastName());
        assertEquals(form.getLogin(), user.getLogin());
    }

    @Test
    public void updateUser() {
        when(userRepo.findById(any())).thenReturn(Optional.ofNullable(user));
        when(userRepo.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);
        User user = userService.updateUser(profileForm);
        assertNotNull(user);
        assertEquals(profileForm.getFirstName(), user.getFirstName());
        assertEquals(profileForm.getLastName(), user.getLastName());
        assertEquals(profileForm.getLogin(), user.getLogin());
        assertEquals(profileForm.isCondition(), user.isCondition());
        assertEquals(profileForm.getPassword(), user.getPassword());
        assertEquals(profileForm.getRole(), user.getRole());
    }

    @Test
    public void ShouldReturnNullIfUserNotFoundWhenGetUserById() {
        when(userRepo.findById(any())).thenReturn(Optional.ofNullable(null));
        User user = userService.getUserById(1L);
        assertNull(user);
    }

    @Test
    public void ShouldReturnUserIfUserNotFoundWhenGetUserById() {
        when(userRepo.findById(any())).thenReturn(Optional.ofNullable(user));
        User user = userService.getUserById(1L);
        assertNotNull(user);
    }

}
