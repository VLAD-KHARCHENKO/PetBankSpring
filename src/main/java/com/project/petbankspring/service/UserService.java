package com.project.petbankspring.service;

import com.project.petbankspring.controller.dto.ProfileForm;
import com.project.petbankspring.controller.dto.RegistrationForm;
import com.project.petbankspring.exception.UserExistException;
import com.project.petbankspring.model.User;
import com.project.petbankspring.model.enums.Role;
import com.project.petbankspring.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {


    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    /**
     * Validates the User's login and verifies whether it corresponds with the Password
     *
     * @param login
     * @param password
     * @return
     */
    public Optional<User> validateUser(String login, String password) {
        User user = userRepo.findByLogin(login);
        return user != null && user.getPassword().equals(password)
                ? Optional.of(user) : Optional.empty();
    }

    /**
     * Converts data from RegistrationForm to User into DB
     *
     * @param form
     * @param role
     * @return
     */
    public User registerUser(RegistrationForm form, Role role) {
        log.info("Register user");
        if (userRepo.existsByLogin(form.getLogin())) {
            throw new UserExistException(String.format("User with login %s already exists", form.getLogin()));
        }

        String password = passwordEncoder.encode(form.getPassword());

        User user = new User(form.getFirstName(), form.getLastName(), form.getLogin(), password, true, role);
        log.info("Save new user: " + user);
        return userRepo.save(user);
    }

    /**
     * Gets current authorized User from Context
     *
     * @return
     * @throws NotFoundException
     */
    public User getCurrentUser() throws NotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (null == auth) {
            throw new NotFoundException("");
        }

        Object obj = auth.getPrincipal();
        String username = "";

        if (obj instanceof UserDetails) {
            username = ((UserDetails) obj).getUsername();
        } else {
            username = obj.toString();
        }

        return userRepo.findByLogin(username);
    }


    public ProfileForm getProfileForm(Long id) {
        User currentUser = getUserById(id);
        return ProfileForm.builder()
                .firstName(currentUser.getFirstName())
                .lastName(currentUser.getLastName())
                .login(currentUser.getLogin())
                .condition(currentUser.isCondition())
                .role(currentUser.getRole())
                .password(currentUser.getPassword())
                .password_confirm(currentUser.getPassword())
                .build();
    }



    /**
     * Takes data from ProfileForm to User and updates it in DB
     *
     * @param profileForm
     * @return
     */
    public User updateUser(ProfileForm profileForm) {
        log.info("Edit profile");
        long id= Long.parseLong(profileForm.getUserId());
        User user = userRepo.findById(id).get();
        user.setLogin(profileForm.getLogin());
        user.setFirstName(profileForm.getFirstName());
        user.setLastName(profileForm.getLastName());
        user.setCondition(profileForm.isCondition());
        user.setRole(profileForm.getRole());
        user.setPassword(profileForm.getPassword());


        log.info("updated user: " + user);
        return userRepo.save(user);
    }

    /**
     * Finds User in DB by ID
     *
     * @param id
     * @return
     */
    public User getUserById(Long id) {
        User userProfile = null;
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            userProfile = user.get();
        } else {
            log.info("Person not found!");
        }
        log.info("user for Edit: " + userProfile);
        return userProfile;
    }



    /**
     * Gets the List of Roles from Enums
     *
     * @return
     */
    public List<String> getRoleNames() {
        return Stream.of(Role.values())
                .map(Role::name)
                .collect(Collectors.toList());
    }

    /**
     * Deletes User from User Repository
     *
     * @param id
     */
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }



    public Page<User> findAll(Pageable pageable) {
        return userRepo.findAll(pageable);
    }
}
