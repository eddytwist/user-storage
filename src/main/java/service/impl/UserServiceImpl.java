package service.impl;

import dao.file.FileUserDao;
import entity.User;
import service.UserService;

import java.util.List;


public class UserServiceImpl implements UserService<User> {
    public static FileUserDao fileUserDao = new FileUserDao();

    @Override
    public boolean createUser(User user) {
        return fileUserDao.createUser(user);
    }

    @Override
    public User getUserById(int id) {
        return fileUserDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return fileUserDao.getAllUsers();
    }

    @Override
    public boolean deleteUser(int id) {
        return fileUserDao.deleteUser(id);
    }

    @Override
    public boolean updateUser(User user) {
        return fileUserDao.updateUser(user);
    }

    public int getLastUserId() {
        List<User> users = getAllUsers();
        return users.get(users.size() - 1).getId();
    }
}