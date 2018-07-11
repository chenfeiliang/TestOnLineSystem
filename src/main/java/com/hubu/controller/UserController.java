package com.hubu.controller;

import com.hubu.dto.UserDTO;
import com.hubu.pojo.Msg;
import com.hubu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    /*
     * 输入：用户信息
     * 操作：存储用户信息
     * 输出：操作结果
     * */
    @RequestMapping("/addUser")
    @ResponseBody
    public Msg addUser(UserDTO userDTO){
//
//        UserDTO userDTO1 = new UserDTO();
//        userDTO1.setAccount("jza");
//        userDTO1.setClassId(1);
//        userDTO1.setImage("aa");
//        userDTO1.setMobile("aaa");
//        userDTO1.setPassword("sss");
//        userDTO1.setRealName("ss");
//        userDTO1.setRePassword("sss");
//        userDTO = userDTO1;

        return  userService.addUser(userDTO) == 1 ? new Msg().success(): new Msg().fail();
    }

    /*
     * 输入：用户Id
     * 操作：删除用户信息
     * 输出：操作结果
     * */
    public Msg deleteUser(){
        return  new Msg().success().add("","");
    }

    /*
     * 输入：用户信息
     * 操作：更新用户信息
     * 输出：操作结果
     * */
    public Msg updateUser(){
        return  new Msg().success().add("","");
    }

    /*
     * 输入：页码
     * 操作：分页查询用户信息
     * 输出：用户列表
     * */
    public Msg getPageUser(int cp){

        return  new Msg().success().add("","");
    }

    /*
     * 输入：页码，关键词
     * 操作：通过关键词分页查询用户信息
     * 输出：用户列表
     * */
    public Msg getPageUserByKeyWord(int cp , String keyword){
        return  new Msg().success().add("","");
    }
}
