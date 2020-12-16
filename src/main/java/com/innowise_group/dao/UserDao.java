package com.innowise_group.dao;

import com.innowise_group.dao.exception.UserNotFoundException;

import java.util.List;

public interface UserDao<User> {

    boolean createUser(User user);

    User getUserById(int id) throws UserNotFoundException;

    List<User> getAllUsers();

    boolean updateUser(User user);

    boolean deleteUser(int id) throws UserNotFoundException;
}
