package com.hubu.controller;

import com.hubu.pojo.Msg;

/*
 * 作者：陈飞良
 * 概述：
 * */
public class QuestionController {

    /*
     * 输入：题目信息
     * 操作：存储题目信息
     * 输出：操作结果
     * */
    public Msg addQuestion(){
        return  new Msg().success().add("","");
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
