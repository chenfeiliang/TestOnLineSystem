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

import javax.annotation.Resource;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    /*
     * 输入：用户信息
     * 操作：存储用户信息
     * 输出：操作结果
     * */
    @RequestMapping(path = "/addUser",method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
    public Msg addUser(UserDTO userDTO){

//        UserDTO userDTO1 = new UserDTO();
//        userDTO1.setAccount("jza");
//        userDTO1.setClassId(1);
//        userDTO1.setImage("aa");
//        userDTO1.setMobile("aaa");
//        userDTO1.setPassword("sss");
//        userDTO1.setRealName("ss");
//        userDTO1.setRePassword("sss");
//        userDTO = userDTO1;

        return  userService.addUser(userDTO);
    }

    /*
     * 输入：account
     * 操作：删除用户信息
     * 输出：操作结果
     * */
    @RequestMapping(path = "/deleteUser",method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
    public Msg deleteUser(String account){
//        account = "guanyu";
        return userService.deleteUser(account);
    }

    /*
     * 输入：用户信息
     * 操作：更新用户信息
     * 输出：操作结果
     * */
    @RequestMapping(path = "/updateUser",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Msg updateUser(User user){
//        user.setAccount("jiabaoyu");
//        user.setClassId(1);
//        user.setImage("aa");
//        user.setMobile("aaa");
//        user.setPassword("sss");
//        user.setRealName("ss");
        return  userService.updateUser(user);
    }

    /*
     * 输入：页码
     * 操作：分页查询用户信息
     * 输出：用户列表
     * */
    @RequestMapping(path = "/getPageUser",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Msg getPageUser(Integer currentPage){
//        Integer currentPage = 1;
        return  userService.findPageUser(currentPage);
    }

    /*
     * 输入：页码，关键词
     * 操作：通过关键词分页查询用户信息
     * 输出：用户列表
     * */
    @RequestMapping("/getPageUserByKeyWord")
//    @ResponseBody
    public Msg getPageUserByKeyWord(int currentPage,String keyword){
//        int currentPage  = 1;
//        String keyword = "z";
        return  userService.getPageUserByKeyWord(currentPage,keyword);
    }
}
