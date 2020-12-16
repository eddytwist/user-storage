package com.innowise_group.service.impl;

import com.innowise_group.dao.UserDao;
import com.innowise_group.entity.User;
import com.innowise_group.service.UserService;

import java.util.List;


public class UserServiceImpl implements UserService<User> {
    public UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean createUser(User user) {
        return userDao.createUser(user);
    }

    @Override
    public User getUserById(int id) {
        return (User) userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public boolean deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    public int getLastUserId() {
        List<User> users = getAllUsers();
        return users.get(users.size() - 1).getId();
    }
}