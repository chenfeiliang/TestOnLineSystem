package com.hubu.service;

import com.hubu.dao.AdminDao;
import com.hubu.dto.AdminDTO;
import com.hubu.pojo.Admin;
import com.hubu.pojo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminDao adminDao;

    /*
     * 输入：管理员信息
     * 操作：校验信息是否符合格式，用户是否存在,合格则，添加用户信息
     * 输出：注册结果
     * */
/*    public Msg register(AdminDTO adminDTO){

    }*/

    /*
     * 输入：账号，密码
     * 操作：验证账号密码，成功则使用Session存储userId,和userName
     * 输出：登录结果
     * */
    public Msg login(String adminName ,String passWord){

        if(adminDao.isExitByName(adminName)>0){
            Admin admin = adminDao.selectAdminByName(adminName);
            if(passWord.equals(admin.getPassWord())){
                return  new Msg().success();
            }
            else {
                return  new Msg().fail().add("errorMsg","密码错误");
            }
        }
        return  new Msg().fail().add("errorMsg","用户名不存在");
    }

}
