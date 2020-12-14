package service;

import java.util.List;

public interface UserService<User> {

    boolean createUser (User user);

    User getUserById(int id);

    List<User> getAllUsers();

    boolean updateUser(User user);

    boolean deleteUser(int id);
}

