package com.innowise_group.dao.file;


import com.innowise_group.db.FileDb;
import com.innowise_group.entity.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FileDbTest {

    private static final String FILE_PATH = "src/main/resources/test/test_storage/test_user_storage.txt";
    List<User> users = new ArrayList<>();
    FileDb fileDbInstance;

    public void initializeList(List<User> list) {
        for (int i = 0; i < 2; i++) {
            String firstName = "UserName" + i;
            String lastName = "UserSurname" + i;
            list.add(new User(firstName, lastName));
            list.get(i).setId((i));
        }
    }

    public void clearList(List<User> list) {
        list.clear();
    }

    @Before
    public void setUp() {
        fileDbInstance = FileDb.getInstance(FILE_PATH);
        initializeList(users);
    }

    @After
    public void finish(){
        clearList(users);
    }

    @Test
    public void testUpdateFile() {
        Assert.assertTrue(fileDbInstance.updateFile(users));
    }

    @Test
    public void testReadFile() {
        Assert.assertEquals(users, fileDbInstance.readFile());
    }
}
