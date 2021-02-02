package com.project.petbankspring.service;

//import com.project.petbankspring.controller.dto.ProfileForm;
//import com.project.petbankspring.controller.dto.RegistrationForm;
//import com.project.petbankspring.controller.dto.UserProfileForm;
//import com.project.petbankspring.exception.UserExistException;
import com.project.petbankspring.controller.dto.RegistrationForm;
import com.project.petbankspring.exception.UserExistException;
import com.project.petbankspring.model.User;
import com.project.petbankspring.model.enums.Role;
import com.project.petbankspring.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepo userRepo;
    @Autowired
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
     * Converts data from RegistrationForm to User and stores it into DB
     *
     * @param form
     * @param role
     * @return
     */
    public User registerUser(RegistrationForm form, Role role) {
        LOG.info("Register user");
        if (userRepo.existsByLogin(form.getLogin())) {
            throw new UserExistException(String.format("User with login %s already exists", form.getLogin()));
        }

        String password = passwordEncoder.encode(form.getPassword());

        User user = new User(form.getFirstName(), form.getLastName(), form.getLogin(), password, true, role);
        LOG.info("Save new user: " + user);
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

    /**
     * Takes data from ProfileForm to User and updates it in DB
     *
     * @param profileForm
     * @return
     */
//    public User updateUser(ProfileForm profileForm) {
//        LOG.info("Edit profile");
//        User user = getCurrentUser();
//        user.setLogin(profileForm.getLogin());
//        user.setFirstName(profileForm.getFirst_name());
//        user.setLastName(profileForm.getLast_name());
//        user.setPhone(profileForm.getPhone());
//
//        LOG.info("updated user: " + user);
//        return userRepo.save(user);
//    }

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
            LOG.info("Person not found!");
        }
        LOG.info("user for Edit: " + userProfile);
        return userProfile;
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
}
