package com.innowise_group.service.impl;

import com.innowise_group.dao.UserDao;
import com.innowise_group.dao.exception.UserNotFoundException;
import com.innowise_group.entity.User;
import com.innowise_group.service.CrudService;

import java.util.List;

public class UserCrudService implements CrudService<User> {
    private final UserDao<User> userDao;

    public UserCrudService(UserDao<User> userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean createUser(User user) {
        return userDao.createUser(user);
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public boolean deleteUser(int id) throws UserNotFoundException {
        return userDao.deleteUser(id);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

}