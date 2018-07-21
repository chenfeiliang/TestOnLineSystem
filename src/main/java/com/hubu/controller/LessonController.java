package com.hubu.controller;

import com.github.pagehelper.PageInfo;
import com.hubu.pojo.Lesson;
import com.hubu.pojo.Msg;
import com.hubu.pojo.MyClass;
import com.hubu.service.LessonService;
import com.hubu.service.PageService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LessonController {
    @Autowired
    LessonService lessonService;
//    @Autowired
//    PageService pageService;
    @RequestMapping(value = "/addLesson",method = {RequestMethod.POST})
    @ResponseBody
    public Msg addLesson(Lesson lesson){
        return lessonService.addLesson(lesson) == 1 ? new Msg().success() : new Msg().fail();
    }

    @RequestMapping(value = "/deleteLesson",method = {RequestMethod.GET})
    @ResponseBody
    public Msg deleteLesson(Integer lessonId){
        return lessonService.deleteLesson(lessonId) == 1 ? new Msg().success() : new Msg().fail();
    }

    @RequestMapping(value = "/updateLesson",method = {RequestMethod.POST})
    @ResponseBody
    public Msg updateLesson(Lesson lesson){
        return lessonService.updateLesson(lesson) == 1 ? new Msg().success() : new Msg().fail();
    }

    @RequestMapping(value = "/getPageLesson",method = {RequestMethod.GET})
    @ResponseBody
    public Msg getPageLesson(Integer currentPage){
        PageInfo<Lesson> pageLesson = lessonService.getPageLesson(currentPage);
        return pageLesson == null ? new Msg().fail() : new Msg().success().add("result",pageLesson);
    }

    @RequestMapping(value = "/getPageLessonByKeyWord",method = {RequestMethod.GET})
    @ResponseBody
    public Msg getPageLessonByKeyWord (Integer currentPage,String keyword){
        PageInfo<Lesson> pageLesson = lessonService.getPageLessonByKeyWord(currentPage,keyword);
        return pageLesson == null ? new Msg().fail() : new Msg().success().add("result",pageLesson);
    }

    @RequestMapping(path = "/getLessonByLessonId",method = {RequestMethod.GET})
    @ResponseBody
    public Msg getLessonByLessonId(Integer lessonId){
        Lesson lesson = lessonService.getLessonByLessonId(lessonId);
        return lesson == null ? new Msg().fail() : new Msg().success().add("result",lesson);
    }

    @RequestMapping(path = "/batchDeleteLessonById",method = RequestMethod.GET)
    @ResponseBody
    public Msg batchDeleteLessonById( String lessonIds){
        return lessonService.batchDeleteLessonById(lessonIds) > 0 ? new Msg().success() : new Msg().fail();
    }

}
