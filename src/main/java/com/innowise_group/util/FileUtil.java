package com.innowise_group.util;

import com.innowise_group.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);
    private static final String FILE_PATH = "storage/user_storage.txt";

    static {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                LOG.info("New file created.");
                updateFile(initializeFile());
            } catch (IOException e) {
                LOG.error("File wasn't created. Details: " + e);
            }
        } else {
            LOG.info("File already exists.");
        }
    }

    public static List<User> readFile() {
        List<User> users = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            users = (List<User>) objectInputStream.readObject();
            LOG.info("Users list successfully found. Details: \n" + users);
        } catch (ClassNotFoundException | IOException e) {
            LOG.error("File wasn't read. Details: " + e);
        }
        return users;
    }

    public static boolean updateFile(List<User> users) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            objectOutputStream.writeObject(users);
            LOG.info("Users list successfully updated. Details: \n" + users);
            return true;
        } catch (IOException e) {
            LOG.error("File wasn't updated. Details: " + e);
            return false;
        }
    }

    public static List<User> initializeFile() {
        List<User> users = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            String firstName = "UserName" + i;
            String lastName = "UserSurname" + i;
            users.add(new User(firstName, lastName));
        }
        LOG.info("File initialized.");
        return users;
    }
}
