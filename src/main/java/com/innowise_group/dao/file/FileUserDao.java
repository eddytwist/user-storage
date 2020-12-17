package com.innowise_group.dao.file;

import com.innowise_group.dao.UserDao;
import com.innowise_group.dao.exception.DaoUserNotFoundException;
import com.innowise_group.db.FileDb;
import com.innowise_group.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FileUserDao implements UserDao<User> {
    private static final Logger LOG = LoggerFactory.getLogger(FileUserDao.class);
    private final String filePath = "storage/user_storage.txt";
    private final FileDb fileDb = FileDb.getInstance(filePath);

    @Override
    public boolean create(User user) {
        List<User> users = fileDb.readFile();
        user.setId(getLastId() + 1);
        users.add(user);
        LOG.info("User successfully created. Details: \n" + user);
        return fileDb.updateFile(users);
    }

    @Override
    public User getById(int id) throws DaoUserNotFoundException {
        List<User> users = fileDb.readFile();
        User user = null;
        for (User u : users) {
            if (u.getId() == id) {
                user = u;
            }
        }
        if (user == null) {
            LOG.error("User wasn't found.");
            throw new DaoUserNotFoundException();
        } else {
            LOG.info("User successfully found. Details: " + user);
            return user;
        }
    }

    @Override
    public List<User> getAll() {
        return fileDb.readFile();
    }

    @Override
    public boolean update(User user) {
        List<User> users = fileDb.readFile();
        for (User u : users) {
            if (u.getId() == user.getId()) {
                users.set(users.indexOf(u), user);
                LOG.info("User successfully updated. Details: " + user);
            }
        }
        return fileDb.updateFile(users);
    }

    @Override
    public boolean delete(int id) throws DaoUserNotFoundException {
        List<User> users = fileDb.readFile();
        User user;
        user = getById(id);
        users.remove(user);
        LOG.info("User successfully deleted. Details: " + users);
        return fileDb.updateFile(users);
    }

    public int getLastId() {
        List<User> users = fileDb.readFile();
        return users.get(users.size() - 1).getId();
    }
}
