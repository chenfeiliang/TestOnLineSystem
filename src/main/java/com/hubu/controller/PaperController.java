package com.hubu.controller;

import com.hubu.pojo.Msg;

public class PaperController {

    /*
     *
     * 输入：一组题目id
     * 操作：手动选择题目，生成，并添加试卷信息
     * 输出：操作结果
     * */
    public Msg addPaperByMan(){
        return new Msg().success().add("","");
    }

    /*
     *
     * 输入：
     * 操作：随机抽取题目，生成，并添加试卷信息
     * 输出：操作结果
     * */
    public Msg addPaperByRandom(){
        return new Msg().success().add("","");
    }

    /*
     * 输入：试卷Id
     * 操作：删除试卷信息
     * 输出：操作结果
     * */
    public Msg deletePaper(){
        return new Msg().success().add("","");
    }

    /*
     * 输入：试卷信息
     * 操作：更新试卷信息
     * 输出：操作结果
     * */
    public Msg updatePaper(){
        return new Msg().success().add("","");
    }


    /*
     * 输入：页码
     * 操作：分页查询试卷信息
     * 输出：题目列表
     * */
    public Msg getPagePaper(int cp){
        return new Msg().success().add("","");
    }

    /*
     * 输入：页码，关键词
     * 操作：通过关键词分页查询试卷信息
     * 输出：试卷列表
     * */
    public Msg getPagePaperByKeyWord(int cp,String keyword){
        return new Msg().success().add("","");
    }

}
