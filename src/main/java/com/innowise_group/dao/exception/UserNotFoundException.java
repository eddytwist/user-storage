package com.innowise_group.dao.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("User doesn't exist.");
    }
}
