package com.innowise_group;

import com.innowise_group.api.UserApi;
import com.innowise_group.dao.UserDao;
import com.innowise_group.dao.file.FileUserDao;
import com.innowise_group.entity.User;
import com.innowise_group.service.Service;
import com.innowise_group.service.impl.UserService;

public class Application {

    public static void main(String[] args) {
        UserDao<User> userDao = new FileUserDao();
        Service<User> service = new UserService(userDao);
        UserApi api = new UserApi(service);
        api.start();
    }
}
