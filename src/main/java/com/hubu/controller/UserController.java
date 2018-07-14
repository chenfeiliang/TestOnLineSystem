package com.hubu.controller;

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
    @RequestMapping(path = "/addUser",method = {RequestMethod.POST})
    @ResponseBody
    public Msg addUser(UserDTO userDTO){
        return userService.addUser(userDTO) == 1 ? new Msg().success() : new Msg().fail();
    }

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
        List<User> pageUser = userService.findPageUser(currentPage);
        return pageUser == null ? new Msg().fail() : new Msg().success().add("result",pageUser);

    }

    /*
     * 输入：页码，关键词
     * 操作：通过关键词分页查询用户信息
     * 输出：用户列表
     * */
    @RequestMapping(value = "/getPageUserByKeyWord",method = {RequestMethod.GET})
    @ResponseBody
    public Msg getPageUserByKeyWord(int currentPage,String keyword){
        List<User> pageUser = userService.getPageUserByKeyWord(currentPage,keyword);
        return pageUser == null ? new Msg().fail() : new Msg().success().add("result",pageUser);
    }
}
