package com.hubu.service;

import com.hubu.dao.UserDAO;
import com.hubu.dto.UserDTO;
import com.hubu.pojo.Msg;
import com.hubu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    private int pageCount = 10;

    public Integer addUser(UserDTO user) {
        try {
            return userDAO.addUser(new User(user));
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public Integer deleteUser(String account) {
        try {
            return userDAO.deleteUser(account);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }

    public Integer updateUser(User user) {

        try {
            return userDAO.updateUser(user);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public List<User> findPageUser(int currentPage) {
        try {
            return userDAO.selectPageUser ((currentPage-1)*pageCount,pageCount);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }

    public List<User> getPageUserByKeyWord(int currentPage, String keyword) {
        try {
            return userDAO.selectPageUserByKeyWord((currentPage-1)*pageCount,pageCount,keyword);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
