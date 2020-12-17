package com.innowise_group.service.impl;

import com.innowise_group.dao.CrudDao;
import com.innowise_group.dao.exception.DaoException;
import com.innowise_group.entity.User;
import com.innowise_group.service.Service;
import com.innowise_group.service.exception.ServiceUserNotFoundException;

import java.util.List;

public class UserService implements Service<User> {
    private final CrudDao<User> crudDao;

    public UserService(CrudDao<User> crudDao) {
        this.crudDao = crudDao;
    }

    @Override
    public boolean create(User user) {
        return crudDao.create(user);
    }

    @Override
    public User getById(int id) throws ServiceUserNotFoundException {
        try {
            return crudDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceUserNotFoundException();
        }
    }

    @Override
    public List<User> getAll() {
        return crudDao.getAll();
    }

    @Override
    public boolean delete(int id) throws ServiceUserNotFoundException {
        try {
            return crudDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceUserNotFoundException();
        }
    }

    @Override
    public boolean update(User user) {
        return crudDao.update(user);
    }

}