package com.hubu.controller;


import com.hubu.dto.UserDTO;
import com.hubu.pojo.Card;
import com.hubu.pojo.Msg;
import com.hubu.pojo.Paper;
import com.hubu.pojo.User;
import com.hubu.service.ExamService;
import com.hubu.service.HomeService;
import com.hubu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    HomeService homeService;
    /*
     * 输入：用户信息
     * 操作：校验信息是否符合格式，用户是否存在,合格则，添加用户信息
     * 输出：注册结果
     * */
    @RequestMapping(path = "/register",method = {RequestMethod.POST})
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
    public Msg login(
            User user,
            HttpSession session
    ) {

        Map<String, Object> map = userService.login(user);

        if (map.containsKey("errMsg"))
            return new Msg().fail().add("result",map.get("errMsg"));
        User userTemp = (User) map.get("user");
        session.setAttribute("user",userTemp.getAccount());

        return new Msg().success();
    }

    @RequestMapping(path = "getExamByAccount",method = {RequestMethod.GET})
    public Msg getExamByAccount(HttpSession session)
    {
        try{
            Object object = session.getAttribute("user");
            String account = String.valueOf(object) ;
            List<Map> examins =   homeService.getExamByAccount(account);
            return new Msg().success().add("result",examins).add("user",account);
        }
        catch(Exception e){
            e.printStackTrace();
            return  new Msg().fail();
        }
    }

    @RequestMapping(path = "/getExamTitleAndTitleByExamId",method = {RequestMethod.GET})
    public Msg getExamTitleAndTitleByExamId(Integer examinId,HttpSession session)
    {
        Object object = session.getAttribute("user");
        String account = String.valueOf(object) ;

        Map<String,Object> result = homeService.getExamTitleAndTimeById(examinId,account);

        return result==null? new Msg().fail().add("errorInfo","你已经参加过考试，请勿重复参加"):new Msg().success().add("result",result).add("user",account);
    }

    /*
     * 输入：
     * 操作：使用Session中的userId，查找试卷信息
     * 输出：试卷信息
     * */
    @RequestMapping("/getCardByExamId")
    public Msg getExamPaperByExamId(Integer examinId,HttpSession session){

        Object object = session.getAttribute("user");
        String account = String.valueOf(object) ;

        return homeService.getCardByExamId(examinId,account);
    }



    /*
     * 输入：
     * 操作：使用Session中的userId，查找对应成绩
     * 输出：成绩信息
     * */
    @RequestMapping("/getScore")
    public Msg getScore(HttpSession session){
        Object object = session.getAttribute("user");
        String account = String.valueOf(object) ;
        System.out.println(account);
        List<Map> maps = homeService.getScore(account);
        return new Msg().success().add("result",maps).add("user",account);
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
