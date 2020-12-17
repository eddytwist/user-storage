package com.innowise_group.dao;

import com.innowise_group.dao.exception.DaoException;

import java.util.List;

public interface CrudDao<T> {

    boolean create(T t);

    T getById(int id) throws DaoException;

    List<T> getAll();

    boolean update(T t);

    boolean delete(int id) throws DaoException;
}
