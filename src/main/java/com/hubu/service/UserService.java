package com.hubu.service;

import com.hubu.dao.UserDAO;
import com.hubu.dto.UserDTO;
import com.hubu.pojo.Msg;
import com.hubu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    public Integer addUser(UserDTO user) {
        return userDAO.addUser(new User(user));
    }
}
