package dao.file;

import dao.UserDao;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FileUtil;

import java.util.List;

public class FileUserDao implements UserDao<User> {
    private static final Logger LOG = LoggerFactory.getLogger(FileUserDao.class);

    @Override
    public boolean createUser (User user) {
        List<User> users =  FileUtil.readFile();
        users.add(user);
        LOG.info("User successfully created. Details: \n" + user);
        return FileUtil.updateFile(users);
    }

    @Override
    public User getUserById(int id) {
        List<User> users = FileUtil.readFile();
        User user = null;
        for (User u: users) {
            if(u.getId() == id) {
                user = u;
            }
        }
        LOG.info("User successfully found.");
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return FileUtil.readFile();
    }

    @Override
    public boolean updateUser(User user) {
        List<User> users = FileUtil.readFile();
        for (User u: users) {
            if(u.getId() == user.getId()) {
                users.set(users.indexOf(u), user);
                LOG.info("User successfully updated. Details: " + user);
            }
        }
        return FileUtil.updateFile(users);
    }

    @Override
    public boolean deleteUser(int id) {
        List<User> users = FileUtil.readFile();
        User user = getUserById(id);
        users.remove(user);
        LOG.info("User successfully deleted. Details: " + users);
        return FileUtil.updateFile(users);
    }


}
