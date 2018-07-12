package com.hubu.controller;

import com.hubu.dto.UserDTO;
import com.hubu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    UserService userService;
    @Test
    public void addUser() {

        UserDTO userDTO = new UserDTO();
        userDTO.setAccount("jza");
        userDTO.setClassId(1);
        userDTO.setImage("aa");
        userDTO.setMobile("aaa");
        userDTO.setPassword("sss");
        userDTO.setRealName("ss");
        userDTO.setRePassword("sss");
        System.out.println(userService.addUser(userDTO));
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void getPageUser() {
    }

    @Test
    public void getPageUserByKeyWord() {
    }
}