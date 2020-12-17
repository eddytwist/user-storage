package com.innowise_group.service.exception;

public class ServiceUserNotFoundException extends ServiceException {
    public ServiceUserNotFoundException() {
        super("User doesn't exist.");
    }
}
