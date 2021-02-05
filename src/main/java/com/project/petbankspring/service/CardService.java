package com.project.petbankspring.service;

import com.project.petbankspring.model.Card;
import com.project.petbankspring.repository.CardRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CardService {

    @Autowired
    private CardRepo cardRepo;
    @Autowired
    private UserService userService;

    public List<Card> findUserCards(){
        return cardRepo.findAllByUserId(userService.getCurrentUser().getId());
    }



    /**
     * Validates the User's login and verifies whether it corresponds with the Password
     *
     * @param login
     * @param password
     * @return
     */


//    public Optional<User> validateUser(String login, String password) {
//        User user = userRepo.findByLogin(login);
//        return user != null && user.getPassword().equals(password)
//                ? Optional.of(user) : Optional.empty();
//    }

    /**
     * Converts data from RegistrationForm to User and stores it into DB
     *
     * @param form
     * @param role
     * @return
     */
//    public User registerUser(RegistrationForm form, Role role) {
//        log.info("Register user");
//        if (userRepo.existsByLogin(form.getLogin())) {
//            throw new UserExistException(String.format("User with login %s already exists", form.getLogin()));
//        }
//
//        String password = passwordEncoder.encode(form.getPassword());
//
//        User user = new User(form.getFirstName(), form.getLastName(), form.getLogin(), password, true, role);
//        log.info("Save new user: " + user);
//        return userRepo.save(user);
//    }

    /**
     * Gets current authorized User from Context
     *
     * @return
     * @throws NotFoundException
     */
//    public Card getCurrentCard() throws NotFoundException {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        if (null == auth) {
//            throw new NotFoundException("");
//        }
//
//        Object obj = auth.getPrincipal();
//        String cardName = "";
//
//        if (obj instanceof UserDetails) {
//            username = ((UserDetails) obj).getUsername();
//        } else {
//            username = obj.toString();
//        }
//
//        return cardRepo.findByLogin(username);
//    }

    /**
     * Takes data from ProfileForm to User and updates it in DB
     *
     * @param profileForm
     * @return
     */
//    public User updateCard (ProfileForm profileForm) {
//        log.info("Edit profile");
//        User user = getCurrentUser();
//        user.setLogin(profileForm.getLogin());
//        user.setFirstName(profileForm.getFirstName());
//        user.setLastName(profileForm.getLastName());
//        user.setCondition(profileForm.isCondition());
//        user.setRole(profileForm.getRole());
//
//
//        log.info("updated user: " + user);
//        return cardRepo.save(user);
//    }

    /**
     * Finds User in DB by ID
     *
     * @param id
     * @return
     */
    public Card getCardById(Long id) {
        Card cardProfile = null;
        Optional<Card> card = cardRepo.findById(id);
        if (card.isPresent()) {
            cardProfile = card.get();
        } else {
            log.info("Card not found!");
        }
        log.info("user for Edit: " + cardProfile);
        return cardProfile;
    }

    /**
     * Uses by admin role to update other users profile
     * Takes data from UserProfileForm to User and updates it in DB
     *
     * @param form
     * @return
     */
//    public User updateUserProfile(UserProfileForm form) {
//        LOG.info("Edit user profile");
//        Long id = Long.parseLong(form.getUserId());
//        User user = getUserById(id);
//
//        user.setLogin(form.getLogin());
//        user.setFirstName(form.getFirst_name());
//        user.setLastName(form.getLast_name());
//        user.setPhone(form.getPhone());
//        user.setRole(Role.valueOf(form.getRole()));
//        LOG.info("updated user: " + user);
//
//        return userRepo.save(user);
//    }

    /**
     * Gets the List of Roles from Enums
     *
     * @return
     */
//    public List<String> getRoleNames() {
//        return Stream.of(Role.values())
//                .map(Role::name)
//                .collect(Collectors.toList());
//    }

    /**
     * Deletes User from User Repository
     *
     * @param id
     */
    public void deleteCard(Long id) {
        cardRepo.deleteById(id);
    }
}
