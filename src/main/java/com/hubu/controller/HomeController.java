package com.hubu.controller;


import com.hubu.dto.UserDTO;
import com.hubu.pojo.Msg;
import com.hubu.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    /*
     * 输入：用户信息
     * 操作：校验信息是否符合格式，用户是否存在,合格则，添加用户信息
     * 输出：注册结果
     * */
    @RequestMapping("/register")
    @ResponseBody
    public Msg register(UserDTO userDTO){
        return new Msg().success().add("hello","hello");
    }

    /*
    * 输入：账号，密码
    * 操作：验证账号密码，成功则使用Session存储userId,和userName
    * 输出：登录结果
    * */
    public Msg login(String userName ,String passWord){
        return new Msg().success();
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
