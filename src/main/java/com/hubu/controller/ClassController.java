package com.hubu.controller;

import com.hubu.pojo.Msg;
import com.hubu.pojo.MyClass;
import com.hubu.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class ClassController {
    @Autowired
    ClassService classService;
    /*
     * 输入：班级信息
     * 操作：存储班级信息
     * 输出：操作结果
     * */
    @RequestMapping(path = "/addClass",method = {RequestMethod.GET,RequestMethod.POST})
//    @ResponseBody
    public Msg addClass(MyClass myClass){
//        MyClass myClass = new MyClass();
//        myClass.setClassName("垃圾软产");
        return classService.addClass(myClass);
    }

    /*
     * 输入：班级Id
     * 操作：删除班级信息
     * 输出：操作结果
     * */
    @RequestMapping(path = "/deleteClass",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Msg deleteClass(Integer classId){
        classId = 5;
        return classService.deleteClass(classId);
    }

    /*
     * 输入：班级信息
     * 操作：更新班级信息
     * 输出：操作结果
     * */
    @RequestMapping(path = "/updateClass",method = {RequestMethod.GET,RequestMethod.POST})
//    @ResponseBody
    public Msg updateClass(MyClass myClass){
//        myClass.setClassId(1);
//        myClass.setClassName("垃圾软产");
        return classService.updateClass(myClass);
    }

    /*
     * 输入：页码
     * 操作：分页查询班级信息
     * 输出：班级列表
     * */
    @RequestMapping(path = "/getPageClass",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Msg getPageClass(Integer currentPage){
        currentPage = 1;
        return  classService.getPageClass(currentPage);
    }

    /*
     * 输入：页码，关键词
     * 操作：通过关键词分页查询班级信息
     * 输出：班级列表
     * */
    @RequestMapping(path = "/getPageClassByKeyWord",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Msg getPageClassByKeyWord(Integer currentPage , String keyword){
        currentPage = 1;
        keyword = "圾";
        return classService.getPageClassByKeyWord(currentPage,keyword);
    }
}
