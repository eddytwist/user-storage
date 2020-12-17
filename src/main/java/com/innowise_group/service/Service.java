package com.innowise_group.service;

import com.innowise_group.service.exception.ServiceException;

import java.util.List;

public interface Service<T> {

    boolean create(T t);

    T getById(int id) throws ServiceException;

    List<T> getAll();

    boolean update(T t);

    boolean delete(int id) throws ServiceException;

}

