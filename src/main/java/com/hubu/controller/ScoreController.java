package com.hubu.controller;

import com.hubu.pojo.Msg;

public class ScoreController {

    /*
     * 输入：班级Id
     * 操作：统计班级所有用户成绩，并分类
     * 输出：操作结果
     * */
    public Msg statAndSortScore(){
        return new Msg().success();
    }

    /*
     * 输入：班级Id
     * 操作：统计班级所有用户成绩，并分类
     * 输出：操作结果
     * */
    public Msg createStatTable(){
        return new Msg().success();
    }
}
