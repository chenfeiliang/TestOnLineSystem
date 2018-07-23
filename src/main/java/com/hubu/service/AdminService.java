package com.hubu.service;

import com.hubu.dao.*;
import com.hubu.dto.QuestionDTO;
import com.hubu.pojo.*;
import com.hubu.utils.DateUtil;
import com.hubu.utils.Myutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {

    @Autowired
    AdminDao adminDao;

    public Msg login( Admin admin){
        Map<String, Object> map = new HashMap<>();
        try {
            int flag = adminDao.isExitByName(admin.getAdminName());
            if (flag <=0){
                return new Msg().fail().add("errorInfo","用户名不存在");
            }
            else {
                Admin admin1 = adminDao.selectAdminByName(admin.getAdminName());
                if (!(admin1.getPassWord().equals(Myutils.MD5(admin.getPassWord())))){
                    return new Msg().fail().add("errorInfo","密码错误");
                }else{
                    return new Msg().success();
                }
            }

        }
        catch (RuntimeException e){
            e.printStackTrace();
            return new Msg().fail().add("error","系统错误");
        }
    }

}
