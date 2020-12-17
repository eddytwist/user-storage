package com.innowise_group.dao.exception;

public class DaoUserNotFoundException extends DaoException {
    public DaoUserNotFoundException() {
        super("User doesn't exist.");
    }
}
