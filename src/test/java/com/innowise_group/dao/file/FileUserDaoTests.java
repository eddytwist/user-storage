package com.innowise_group.dao.file;

import com.innowise_group.entity.User;
import com.innowise_group.util.FileUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FileUserDaoTests {

    private static final String TEST_FILE_PATH = "/src/main/resources/test/test_storage/test_user_storage.txt";


    @Test
    public static void testReadFile() {
        List<User> testList = (List<User>) FileUtil.readFile();
        List<User> expectedList = null;
        for (int i = 1; i < 3; i++) {
            String firstName = "UserName" + i;
            String lastName = "UserSurname" + i;
            expectedList.add(new User(firstName, lastName));
        }
        Assert.assertEquals(expectedList, testList);
    }

}
