package com.project.petbankspring.exception;

public class UserExistException extends RuntimeException {

    public UserExistException() {
    }

    public UserExistException(String message) {
        super(message);
    }
}
