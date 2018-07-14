package com.hubu.controller;

import com.hubu.dto.UserDTO;
import com.hubu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

public class UserControllerTest {
    @Test
    public void test1(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("12345");
        stringBuilder.setLength(stringBuilder.length()-1);
        System.out.println(stringBuilder);
    }
}