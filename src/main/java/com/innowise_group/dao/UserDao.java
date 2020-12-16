package com.innowise_group.dao;

import com.innowise_group.dao.exception.UserNotFoundException;

import java.util.List;

public interface UserDao<T> {

    boolean createUser(T t);

    T getUserById(int id) throws UserNotFoundException;

    List<T> getAllUsers();

    boolean updateUser(T t);

    boolean deleteUser(int id) throws UserNotFoundException;
}
