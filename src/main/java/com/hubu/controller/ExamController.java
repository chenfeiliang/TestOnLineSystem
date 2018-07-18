package com.hubu.controller;

import com.hubu.pojo.Examin;
import com.hubu.pojo.Msg;
import com.hubu.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public Msg deleteExam(Integer examId){
        return examService.deleteExam(examId) == 1 ? new Msg().success() : new Msg().fail();
    }
}
