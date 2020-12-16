package com.innowise_group.dao.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("User doesn't exist.");
    }
}
