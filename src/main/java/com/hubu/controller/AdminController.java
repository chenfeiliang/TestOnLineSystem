package com.hubu.controller;


import com.hubu.dao.AdminDao;
import com.hubu.pojo.Admin;
import com.hubu.pojo.Msg;
import com.hubu.pojo.User;
import com.hubu.service.AdminService;
import com.hubu.service.HomeService;
import com.hubu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {


    @Autowired
    AdminService adminService;

    /*
     * 输入：账号，密码
     * 操作：验证账号密码，成功则使用Session存储userId,和userName
     * 输出：登录结果
     * */

    @RequestMapping(path = "/adminLogin",method = {RequestMethod.POST,RequestMethod.GET})
    public Msg login(
            Admin admin,
            HttpSession session
    )
    {
        Msg msg = adminService.login(admin);
        if(msg.getCode()==200){
            session.setAttribute("admin",admin.getAdminName());
        }
        return msg;
    }


}
