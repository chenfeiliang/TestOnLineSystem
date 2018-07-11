package com.hubu.controller;

import com.hubu.pojo.Msg;

public class UserController {
    /*
     * 输入：用户信息
     * 操作：存储用户信息
     * 输出：操作结果
     * */
    public Msg addUser(){
        return  new Msg().success().add("","");
    }

    /*
     * 输入：用户Id
     * 操作：删除用户信息
     * 输出：操作结果
     * */
    public Msg deleteUser(){
        return  new Msg().success().add("","");
    }

    /*
     * 输入：用户信息
     * 操作：更新用户信息
     * 输出：操作结果
     * */
    public Msg updateUser(){
        return  new Msg().success().add("","");
    }

    /*
     * 输入：页码
     * 操作：分页查询用户信息
     * 输出：用户列表
     * */
    public Msg getPageUser(int cp){
        return  new Msg().success().add("","");
    }

    /*
     * 输入：页码，关键词
     * 操作：通过关键词分页查询用户信息
     * 输出：用户列表
     * */
    public Msg getPageUserByKeyWord(int cp , String keyword){
        return  new Msg().success().add("","");
    }
}
