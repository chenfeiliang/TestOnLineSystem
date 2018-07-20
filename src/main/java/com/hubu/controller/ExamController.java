package com.hubu.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hubu.pojo.Examin;
import com.hubu.pojo.Msg;
import com.hubu.pojo.User;
import com.hubu.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ExamController {
    @Autowired
    ExamService examService;
    /*
     * 输入：试卷Id，班级，时间

     * 操作：手动选择试卷，班级，时间，生成一场考试，并添加到表
     * 输出：操作结果
     *
     * */
    @RequestMapping(path = "/addExam",method = {RequestMethod.POST})
    public Msg addExam(Examin examin){
        return examService.addExam(examin) == 1 ? new Msg().success() : new Msg().fail();
    }

    @RequestMapping(path = "/deleteExam",method = {RequestMethod.GET})
    public Msg deleteExam(String examinIds){
        return examService.deleteExam(examinIds) > 0 ? new Msg().success() : new Msg().fail();
    }
    @RequestMapping(path = "/updateExam",method = {RequestMethod.POST})
    public Msg updateExam(Examin examin){
        return examService.updateExam(examin) == 1 ? new Msg().success() : new Msg().fail();
    }
    @RequestMapping(path = "/getPageExam",method = {RequestMethod.GET})
    public Msg getPageExam(Integer currentPage){
        PageInfo<Examin> pageExam = examService.getPageExam(currentPage);
        return pageExam == null ? new Msg().fail() : new Msg().success().add("result",pageExam);
    }
    @RequestMapping(path = "/getPageExamByKeyWord",method = {RequestMethod.GET})
    public Msg getPageExamByKeyWord(Integer currentPage,String keyword){
        PageInfo<Examin> pageExam = examService.getPageExamByKeyWord(currentPage,keyword);
        return pageExam == null ? new Msg().fail() : new Msg().success().add("result",pageExam);
    }
    @RequestMapping(path = "/getExamByExaminId")
    public Msg getExamByExaminId(Integer examinId){
        Examin examin = examService.getExamByExaminId(examinId);
        return examin == null ? new Msg().fail() : new Msg().success().add("result",examin);
    }

//    @RequestMapping(path = "/getExamByAccount")
//    public Msg getExamByAccount()

    @RequestMapping(path = "/getExam",method = {RequestMethod.GET})
    public Msg getExam(
            HttpSession session,
            Integer lessonId,
            String account
    ){
        User user = (User) session.getAttribute("user");
        Map<String,Object> map = examService.getExamByLessonIdAndAccount(lessonId,account);
        map.put("user",user);
        return map.containsKey("rest") ? new Msg().success().add("result",map) : new Msg().fail();
    }
}
