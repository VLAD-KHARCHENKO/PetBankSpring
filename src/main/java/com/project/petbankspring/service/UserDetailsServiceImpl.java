package com.project.petbankspring.service;

import com.project.petbankspring.model.User;
import com.project.petbankspring.repository.UserRepo;
import com.project.petbankspring.service.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;
    private final HttpSession session;
    private final UserService userService;



    /**
     * Finds User in the DB by Login and loads User in the session
     *
     * @param login
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepo.findByLogin(login);

        if (user == null || !user.isCondition()) {
            throw new UsernameNotFoundException("User not found or blocked! Username : " + login);
        }


        session.setAttribute("user", user);
        return new CustomUserDetails(user);
    }
}
