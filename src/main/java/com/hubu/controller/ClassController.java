package com.hubu.controller;

import com.github.pagehelper.PageInfo;
import com.hubu.pojo.Msg;
import com.hubu.pojo.MyClass;
import com.hubu.service.ClassService;
import com.hubu.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ClassController {
    @Autowired
    ClassService classService;
    @Autowired
    PageService pageService;
    /*
     * 输入：班级信息
     * 操作：存储班级信息
     * 输出：操作结果
     * */
    @RequestMapping(path = "/addClass",method = {RequestMethod.POST})
    @ResponseBody
    public Msg addClass(
                        MyClass myClass,
                        HttpServletResponse response,
                        HttpServletRequest request
    ){
//        String origin = request.getHeader("Origin");
//        response.setHeader("Access-Control-Allow-Origin","http://120.79.59.117".equals(origin) ? origin : "");
        return classService.addClass(myClass) == 1 ? new Msg().success() : new Msg().fail();
    }

    /*
     * 输入：班级Id
     * 操作：删除班级信息
     * 输出：操作结果
     * */
    @RequestMapping(path = "/deleteClass",method = {RequestMethod.GET})
    @ResponseBody
    public Msg deleteClass(
                            Integer classId,
                            HttpServletResponse response,
                            HttpServletRequest request
    ){
//        String origin = request.getHeader("Origin");
//        response.setHeader("Access-Control-Allow-Origin","http://120.79.59.117".equals(origin) ? origin : "");
        return classService.deleteClass(classId) == 1 ? new Msg().success() : new Msg().fail();
    }

    /*
     * 输入：班级信息
     * 操作：更新班级信息
     * 输出：操作结果
     * */
    @RequestMapping(path = "/updateClass",method = {RequestMethod.POST})
    @ResponseBody
    public Msg updateClass(
                            MyClass myClass,
                            HttpServletResponse response,
                            HttpServletRequest request
    ){
//        String origin = request.getHeader("Origin");
//        response.setHeader("Access-Control-Allow-Origin","http://120.79.59.117".equals(origin) ? origin : "");
        return classService.updateClass(myClass) == 1 ? new Msg().success() : new Msg().fail();
    }

    /*
     * 输入：页码
     * 操作：分页查询班级信息
     * 输出：班级列表
     * */
    @RequestMapping(path = "/getPageClass",method = {RequestMethod.GET})
    @ResponseBody
    public Msg getPageClass(
                            Integer currentPage,
                            HttpServletResponse response,
                            HttpServletRequest request
    ){
        PageInfo<MyClass> pageClass = classService.getPageClass(currentPage);
        return pageClass == null ? new Msg().fail() : new Msg().success().add("result",pageClass);
    }



    /*
     * 输入：页码，关键词
     * 操作：通过关键词分页查询班级信息
     * 输出：班级列表
     * */
    @RequestMapping(path = "/getPageClassByKeyWord",method = {RequestMethod.GET})
    @ResponseBody
    public Msg getPageClassByKeyWord(
                                     Integer currentPage ,
                                     String keyword,
                                     HttpServletResponse response,
                                     HttpServletRequest request
    ){
        PageInfo<MyClass> pageClass = classService.getPageClassByKeyWord(currentPage,keyword);
        return pageClass == null ? new Msg().fail() : new Msg().success().add("result",pageClass);
    }

    @RequestMapping(path = "/getClassByClassId",method = {RequestMethod.GET})
    @ResponseBody
    public Msg getClassByClassId(
                                Integer classId,
                                HttpServletResponse response,
                                HttpServletRequest request
    ){
//        String origin = request.getHeader("Origin");
//        response.setHeader("Access-Control-Allow-Origin","http://120.79.59.117".equals(origin) ? origin : "");
        MyClass myClass = classService.getClassByClassId(classId);
        return myClass == null ? new Msg().fail() : new Msg().success().add("result",myClass);
    }
    @RequestMapping(path = "/batchDeleteClassById",method = RequestMethod.GET)
    @ResponseBody
    public Msg batchDeleteClassById(
                                    String classIds,
                                    HttpServletResponse response,
                                    HttpServletRequest request
    ){
//        String origin = request.getHeader("Origin");
//        response.setHeader("Access-Control-Allow-Origin","http://120.79.59.117".equals(origin) ? origin : "");
        return classService.batchDeleteClassById(classIds) > 0 ? new Msg().success() : new Msg().fail();
    }
}
