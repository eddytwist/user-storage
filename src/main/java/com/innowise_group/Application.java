package com.innowise_group;

import com.innowise_group.api.UserApi;
import com.innowise_group.dao.UserDao;
import com.innowise_group.dao.file.FileUserDao;
import com.innowise_group.entity.User;
import com.innowise_group.service.UserService;
import com.innowise_group.service.impl.UserServiceImpl;


public class Application {

    public static void main(String[] args) {
        UserDao<User> userDao = new FileUserDao();
        UserService<User> userService = new UserServiceImpl(userDao);
        UserApi api = new UserApi(userService);
        api.start();
    }
}
