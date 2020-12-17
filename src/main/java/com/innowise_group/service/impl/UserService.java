package com.innowise_group.service.impl;

import com.innowise_group.dao.UserDao;
import com.innowise_group.dao.exception.DaoException;
import com.innowise_group.entity.User;
import com.innowise_group.service.Service;
import com.innowise_group.service.exception.ServiceUserNotFoundException;

import java.util.List;

public class UserService implements Service<User> {
    private final UserDao<User> userDao;

    public UserService(UserDao<User> userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean create(User user) {
        return userDao.create(user);
    }

    @Override
    public User getById(int id) throws ServiceUserNotFoundException {
        try {
            return userDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceUserNotFoundException();
        }
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public boolean delete(int id) throws ServiceUserNotFoundException {
        try {
            return userDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceUserNotFoundException();
        }
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

}