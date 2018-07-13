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

    public Msg addUser(UserDTO user) {
        return userDAO.addUser(new User(user)) == 1 ? new Msg().success() : new Msg().fail();
    }

    public Msg deleteUser(String account) {
        return userDAO.deleteUser(account) == 1 ? new Msg().success() : new Msg().fail();
    }

    public Msg updateUser(User user) {
        return userDAO.updateUser(user) == 1 ? new Msg().success() : new Msg().fail();
    }

    public Msg findPageUser(int currentPage) {
        try {
            return new Msg().success().add("result",userDAO.selectPageUser ((currentPage-1)*pageCount,pageCount));
        }catch (Exception e){
            e.printStackTrace();
            return new Msg().fail();
        }


    }

    public Msg getPageUserByKeyWord(int currentPage, String keyword) {
        try {
            return new Msg().success().add("result",userDAO.selectPageUserByKeyWord((currentPage-1)*pageCount,pageCount,keyword));
        }catch (OutOfMemoryError e){
            return new Msg().fail();
        }

    }
}
