package com.innowise_group;

import com.innowise_group.api.UserApi;
import com.innowise_group.dao.UserDao;
import com.innowise_group.dao.file.FileUserDao;
import com.innowise_group.entity.User;
import com.innowise_group.service.CrudService;
import com.innowise_group.service.impl.UserCrudService;

public class Application {

    public static void main(String[] args) {
        UserDao<User> userDao = new FileUserDao();
        CrudService<User> crudService = new UserCrudService(userDao);
        UserApi api = new UserApi(crudService);
        api.start();
    }
}
