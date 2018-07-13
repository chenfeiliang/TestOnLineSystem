package com.hubu.controller;

import com.hubu.pojo.Lesson;
import com.hubu.pojo.Msg;
import com.hubu.pojo.Question;
import com.hubu.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * 作者：陈飞良
 * 概述：
 * */
@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;
    /*
     * 输入：题目信息
     * 操作：存储题目信息
     * 输出：操作结果
     * */
    @RequestMapping(path = "/addQuestion",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Msg addQuestion( Question question){
        question.setTitle("Title");
        question.setOptionA("OptionA");
        question.setOptionB("OptionB");
        question.setOptionC("OptionC");
        question.setOptionD("OptionD");
        question.setCreator("Creator");
        question.setQuestionKey("Key");
        question.setQuestionLevel(1);
        question.setLessonId(2);
        Lesson lesson = new Lesson();
        lesson.setLessonId(2);
        lesson.setLessonName("java");
        question.setLesson(lesson);

        return questionService.addQuestion(question);
    }

    /*
     * 输入：题目Id
     * 操作：删除题目信息
     * 输出：操作结果
     * */
    public Msg deleteQuestion(){
        return  new Msg().success().add("","");
    }

    /*
     * 输入：题目信息
     * 操作：更新题目信息
     * 输出：操作结果
     * */
    public Msg updateQuestion(){
        return  new Msg().success().add("","");
    }

    /*
     * 输入：页码
     * 操作：分页查询题目信息
     * 输出：题目列表
     * */
    public Msg getPageQuestion(int cp){
        return  new Msg().success().add("","");
    }

    /*
     * 输入：页码，关键词
     * 操作：通过关键词分页查询题目信息
     * 输出：题目列表
     * */
    public Msg getPageQuestionByKeyWord(int cp , String keyword){
        return  new Msg().success().add("","");
    }

}
