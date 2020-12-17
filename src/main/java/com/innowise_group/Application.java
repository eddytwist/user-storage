package com.innowise_group;

import com.innowise_group.api.UserApi;
import com.innowise_group.dao.CrudDao;
import com.innowise_group.dao.file.FileCrudDao;
import com.innowise_group.entity.User;
import com.innowise_group.service.Service;
import com.innowise_group.service.impl.UserService;

public class Application {

    public static void main(String[] args) {
        CrudDao<User> crudDao = new FileCrudDao();
        Service<User> service = new UserService(crudDao);
        UserApi api = new UserApi(service);
        api.start();
    }
}
