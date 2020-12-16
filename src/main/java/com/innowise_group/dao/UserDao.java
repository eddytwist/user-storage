package com.innowise_group.dao;

import java.util.List;

public interface UserDao<User> {

    boolean createUser (User user);

    User getUserById(int id);

    List<User> getAllUsers();

    boolean updateUser(User user);

    boolean deleteUser(int id);
}
