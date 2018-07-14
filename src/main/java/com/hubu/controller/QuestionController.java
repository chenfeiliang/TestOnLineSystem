package com.hubu.controller;

import com.hubu.pojo.Lesson;
import com.hubu.pojo.Msg;
import com.hubu.pojo.Question;
import com.hubu.service.QuestionService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.krb5.internal.PAData;

import java.util.List;

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
    @RequestMapping(path = "/addQuestion",method = {RequestMethod.POST})
    @ResponseBody
    public Msg addQuestion( Question question){
        return questionService.addQuestion(question) == 1 ? new Msg().success() : new Msg().fail();
    }

    /*
     * 输入：题目Id
     * 操作：删除题目信息
     * 输出：操作结果
     * */
    @RequestMapping(path = "/deleteQuestion",method = {RequestMethod.GET})
    @ResponseBody
    public Msg deleteQuestion(Integer questionId){
        return questionService.deleteQuestion(questionId) == 1 ? new Msg().success() : new Msg().fail();
    }

    /*
     * 输入：题目信息
     * 操作：更新题目信息
     * 输出：操作结果
     * */
    @RequestMapping(path = "/updateQuestion",method = {RequestMethod.POST})
    @ResponseBody
    public Msg updateQuestion(Question question){
        return questionService.updateQuestion(question) == 1 ? new Msg().success() : new Msg().fail();
    }

    /*
     * 输入：页码
     * 操作：分页查询题目信息
     * 输出：题目列表
     * */
    @RequestMapping(path = "/getPageQuestion",method = {RequestMethod.GET})
    @ResponseBody
    public Msg getPageQuestion(Integer currentPage){
        List<Question> pageQuestion = questionService.getPageQuestion(currentPage);
        return pageQuestion == null ? new Msg().fail() : new Msg().success().add("result",pageQuestion);
    }

    /*
     * 输入：页码，关键词
     * 操作：通过关键词分页查询题目信息
     * 输出：题目列表
     * */
    @RequestMapping(path = "/getPageQuestionByKeyWord",method = {RequestMethod.GET})
    @ResponseBody
    public Msg getPageQuestionByKeyWord(Integer currentPage , String keyword){
        List<Question> pageQuestion = questionService.getPageQuestionByKeyWord(currentPage,keyword);
        return pageQuestion == null ? new Msg().fail() : new Msg().success().add("result",pageQuestion);
    }

}
