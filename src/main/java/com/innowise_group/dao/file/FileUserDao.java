package com.innowise_group.dao.file;

import com.innowise_group.dao.UserDao;
import com.innowise_group.dao.exception.UserNotFoundException;
import com.innowise_group.entity.User;
import com.innowise_group.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FileUserDao implements UserDao<User> {
    private static final Logger LOG = LoggerFactory.getLogger(FileUserDao.class);

    @Override
    public boolean createUser(User user) {
        List<User> users = FileUtil.readFile();
        user.setId(getLastId() + 1);
        users.add(user);
        LOG.info("User successfully created. Details: \n" + user);
        return FileUtil.updateFile(users);
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        List<User> users = FileUtil.readFile();
        User user = null;
        for (User u : users) {
            if (u.getId() == id) {
                user = u;
            }
        }
        if (user == null) {
            LOG.error("User wasn't found.");
            throw new UserNotFoundException();
        } else {
            LOG.info("User successfully found. Details: " + user);
            return user;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return FileUtil.readFile();
    }

    @Override
    public boolean updateUser(User user) {
        List<User> users = FileUtil.readFile();
        for (User u : users) {
            if (u.getId() == user.getId()) {
                users.set(users.indexOf(u), user);
                LOG.info("User successfully updated. Details: " + user);
            }
        }
        return FileUtil.updateFile(users);
    }

    @Override
    public boolean deleteUser(int id) throws UserNotFoundException {
        List<User> users = FileUtil.readFile();
        User user;
        user = getUserById(id);
        users.remove(user);
        LOG.info("User successfully deleted. Details: " + users);
        return FileUtil.updateFile(users);
    }

    public int getLastId() {
        List<User> users = FileUtil.readFile();
        return users.get(users.size() - 1).getId();
    }
}
