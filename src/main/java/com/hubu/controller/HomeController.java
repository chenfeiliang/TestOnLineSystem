package com.hubu.controller;


import com.hubu.dto.UserDTO;
import com.hubu.pojo.Msg;
import com.hubu.pojo.Paper;
import com.hubu.pojo.User;
import com.hubu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    UserService userService;
    /*
     * 输入：用户信息
     * 操作：校验信息是否符合格式，用户是否存在,合格则，添加用户信息
     * 输出：注册结果
     * */
    @RequestMapping(path = "/register",method = {RequestMethod.POST})
    @ResponseBody
    public Msg register(User user){
        Map<String, Object> map = userService.addUser(user);
        if (map.containsKey("errMsg"))
            return new Msg().fail().add("result",map.get("errMsg"));
        return new Msg().success();
    }

    /*
    * 输入：账号，密码
    * 操作：验证账号密码，成功则使用Session存储userId,和userName
    * 输出：登录结果
    * */
    @RequestMapping(path = "/login",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Msg login(
            User user,
            HttpSession session
    ) {
        Map<String, Object> map = userService.login(user);
        if (map.containsKey("errMsg"))
            return new Msg().fail().add("result",map.get("errMsg"));
        session.setAttribute("user",map.get("user"));
        return new Msg().success().add("result",map.get("user"));
    }
    /*
     * 输入：
     * 操作：使用Session中的userId，查找试卷信息
     * 输出：试卷信息
     * */
    public Msg getPaper(){

        Paper paper = null;

        return new Msg().success().add("paper",paper);
    }

    /*
     * 输入：
     * 操作：使用Session中的userId，存储试卷信息
     * 输出：存储结果
     * */
    public Msg savePaper(Paper paper){
        return new Msg().success().add("","");
    }

    /*
     * 输入：
     * 操作：使用Session中的userId，查找对应成绩
     * 输出：成绩信息
     * */
    public Msg getScore(){
        return new Msg().success().add("","");
    }

    /*
     * 输入：
     * 操作：使用Session中的userId，查找对应错题集
     * 输出：错题集
     * */
    public Msg getError(){
        return new Msg().success().add("","");
    }
}
