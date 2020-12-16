package com.innowise_group.service;

import com.innowise_group.dao.exception.UserNotFoundException;

import java.util.List;

public interface CrudService<T> {

    boolean createUser(T t);

    T getUserById(int id) throws UserNotFoundException;

    List<T> getAllUsers();

    boolean updateUser(T t);

    boolean deleteUser(int id) throws UserNotFoundException;

}

