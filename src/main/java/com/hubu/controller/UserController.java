package com.hubu.controller;

import com.github.pagehelper.PageInfo;
import com.hubu.dto.UserDTO;
import com.hubu.pojo.Msg;
import com.hubu.pojo.User;
import com.hubu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class UserController {
    @Autowired
    UserService userService;
    /*
     * 输入：用户信息
     * 操作：存储用户信息
     * 输出：操作结果
     * */
//    @RequestMapping(path = "/addUser",method = {RequestMethod.POST})
//    @ResponseBody
//    public Msg addUser(User user){
//        return userService.addUser(user) == 1 ? new Msg().success() : new Msg().fail();
//    }

    /*
     * 输入：account
     * 操作：删除用户信息
     * 输出：操作结果
     * */
    @RequestMapping(path = "/deleteUser",method = {RequestMethod.GET})
    @ResponseBody
    public Msg deleteUser(String account){
        return userService.deleteUser(account) == 1 ? new Msg().success() : new Msg().fail();
    }

    /*
     * 输入：用户信息
     * 操作：更新用户信息
     * 输出：操作结果
     * */
    @RequestMapping(path = "/updateUser",method = {RequestMethod.POST})
    @ResponseBody
    public Msg updateUser(User user){
        return userService.updateUser(user) == 1 ? new Msg().success() : new Msg().fail();
    }

    /*
     * 输入：页码
     * 操作：分页查询用户信息
     * 输出：用户列表
     * */
    @RequestMapping(path = "/getPageUser",method = {RequestMethod.GET})
    @ResponseBody
    public Msg getPageUser(Integer currentPage){
        PageInfo<User> pageClass = userService.getPageUser(currentPage);
        return pageClass == null ? new Msg().fail() : new Msg().success().add("result",pageClass);

    }

    /*
     * 输入：页码，关键词
     * 操作：通过关键词分页查询用户信息
     * 输出：用户列表
     * */
    @RequestMapping(value = "/getPageUserByKeyWord",method = {RequestMethod.GET})
    @ResponseBody
    public Msg getPageUserByKeyWord(int currentPage,String keyword){
        PageInfo<User> pageClass = userService.getPageUserByKeyWord(currentPage,keyword);
        return pageClass == null ? new Msg().fail() : new Msg().success().add("result",pageClass);
    }

    @RequestMapping(path = "/getUserByAccount",method = {RequestMethod.GET})
    @ResponseBody
    public Msg getUserByAccount(String account){
        User user = userService.getUserByUserId(account);
        return user == null ? new Msg().fail() : new Msg().success().add("result",user);
    }
}
