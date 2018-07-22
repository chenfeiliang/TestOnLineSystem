package com.hubu.dao;

import com.hubu.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDAOTest {

    @Autowired

    UserDAO userDAO ;
    @Test
    public void selectUsers() {
        List<User> userList = userDAO.selectUsers("1111','111111','1231");
        System.out.println(userList.size());
    }
}