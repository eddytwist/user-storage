package com.innowise_group.service;

import com.innowise_group.dao.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService<User> {

    boolean createUser (User user);

    User getUserById(int id) throws UserNotFoundException;

    List<User> getAllUsers();

    boolean updateUser(User user);

    boolean deleteUser(int id) throws UserNotFoundException;

    int getLastUserId();
}

