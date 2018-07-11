package com.hubu.controller;

import com.hubu.pojo.Msg;

public class ClassController {
    /*
     * 输入：班级信息
     * 操作：存储班级信息
     * 输出：操作结果
     * */
    public Msg addClass(){
        return  new Msg().success().add("","");
    }

    /*
     * 输入：班级Id
     * 操作：删除班级信息
     * 输出：操作结果
     * */
    public Msg deleteClass(){
        return  new Msg().success().add("","");
    }

    /*
     * 输入：班级信息
     * 操作：更新班级信息
     * 输出：操作结果
     * */
    public Msg updateClass(){
        return  new Msg().success().add("","");
    }

    /*
     * 输入：页码
     * 操作：分页查询班级信息
     * 输出：班级列表
     * */
    public Msg getPageClass(int cp){
        return  new Msg().success().add("","");
    }

    /*
     * 输入：页码，关键词
     * 操作：通过关键词分页查询班级信息
     * 输出：班级列表
     * */
    public Msg getPageClassByKeyWord(int cp , String keyword){
        return  new Msg().success().add("","");
    }
}
