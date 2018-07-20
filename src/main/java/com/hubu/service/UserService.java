package com.hubu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hubu.dao.UserDAO;
import com.hubu.dto.UserDTO;
import com.hubu.pojo.Msg;
import com.hubu.pojo.User;
import com.hubu.utils.Myutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    private int pageCount = 10;

    public Map<String,Object> addUser(User user) {
        Map<String, Object> map = new HashMap<>();
        try {
            User userResult = userDAO.selectUserByUserId(user.getAccount());
            if (userResult != null){
                map.put("errMsg","用户已存在");
                return map;
            }
            user.setSalt(UUID.randomUUID().toString().substring(0,5));
            user.setPassword(Myutils.MD5(user.getPassword() + user.getSalt()));
            user.setImage(String.format("https://images.nowcoder.com/head/%dm.png", new Random().nextInt(1000)));
            userDAO.addUser(user);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put("errMsg","注册失败");
            return map;
        }
    }

    public Integer deleteUser(String account) {
        try {
            String s = account.replaceAll(",", "','");
            return userDAO.deleteUser(s);
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

    public PageInfo<User> getPageUser(int currentPage) {
        try {
            PageHelper.startPage(currentPage, 7);
            List<User> users = userDAO.selectUser();
            PageInfo<User> pageUser = new PageInfo<>(users);
            int[] nums = pageUser.getNavigatepageNums();
            int[] result = Myutils.pageCount(currentPage, nums);
            pageUser.setNavigatepageNums(result);
            return pageUser;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public PageInfo<User> getPageUserByKeyWord(int currentPage, String keyword) {
        try {
            PageHelper.startPage(currentPage, 7);
            List<User> users = userDAO.selectUserByKeyWord(keyword);
            PageInfo<User> pageUser = new PageInfo<>(users);
            int[] nums = pageUser.getNavigatepageNums();
            int[] result = Myutils.pageCount(currentPage, nums);
            pageUser.setNavigatepageNums(result);
            return pageUser;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public User getUserByUserId(String account) {
        try {
            return userDAO.selectUserByUserId(account);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, Object> login(User user) {
        Map<String, Object> map = new HashMap<>();
        try {
            User userResult = userDAO.selectUserByUserId(user.getAccount());
            if (userResult == null){
                map.put("errMsg","用户名不存在");
                return map;
            }
            else {
                if ( !Myutils.MD5(user.getPassword() + userResult.getSalt()).equals(userResult.getPassword())){
                    map.put("errMsg","密码错误");
                    return map;
                }
            }
            map.put("user",userResult);
            return map;
        }catch (RuntimeException e){
            e.printStackTrace();
            map.put("errMsg","登陆失败");
            return map;
        }
    }

    public PageInfo<User> getUserByClassId(Integer classId,Integer currentPage) {
        try {

            PageHelper.startPage(currentPage, 7);
            List<User> users = userDAO.selectUserByClassId(classId);
            PageInfo<User> pageUser = new PageInfo<>(users);
            int[] nums = pageUser.getNavigatepageNums();
            int[] result = Myutils.pageCount(currentPage, nums);
            pageUser.setNavigatepageNums(result);
            return pageUser;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
