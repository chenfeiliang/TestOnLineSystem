package com.hubu.controller;

import com.hubu.dto.AdminDTO;
import com.hubu.pojo.Msg;
import com.hubu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;
    /*
     * 输入：管理员信息
     * 操作：校验信息是否符合格式，用户是否存在,合格则，添加用户信息
     * 输出：注册结果
     * */
/*    public Msg register(AdminDTO adminDTODTO){
        return new Msg().success().add("","");
    }*/

    /*
     * 输入：账号，密码
     * 操作：验证账号密码，成功则使用Session存储userId,和userName
     * 输出：登录结果
     * */
    @RequestMapping("/adminLogin")
    @ResponseBody
    public Msg adminLogin(String adminName ,String passWord){
        return adminService.login(adminName,passWord);
    }
}
