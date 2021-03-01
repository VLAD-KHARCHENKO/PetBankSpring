package com.project.petbankspring.service.security;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);

}
