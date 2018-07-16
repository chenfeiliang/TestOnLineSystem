package com.hubu.controller;

import com.hubu.pojo.Lesson;
import com.hubu.pojo.Msg;
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
    @Autowired
    PageService pageService;
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
        Msg msg = new Msg();
/*        List<Lesson> pageLesson = lessonService.getPageLesson(currentPage);
        Integer pageCount = pageService.getPageCount("lesson",null);
        if (pageLesson == null || pageCount == -1)
            return msg.fail();
        msg = msg.success();
        msg.add("result",pageLesson);
        msg.add("pageCount",pageCount);*/
        return msg;
    }

        @RequestMapping(value = "/getPageLessonByKeyWord",method = {RequestMethod.GET})
    @ResponseBody
    public Msg getPageLessonByKeyWord (Integer currentPage,String keyword){
        Msg msg = new Msg();
/*        List<Lesson> pageClass = lessonService.getPageLessonByKeyWord(currentPage, keyword);
        Integer pageCount = pageService.getPageCount("lesson",keyword);
        if (pageClass == null || pageCount == -1)
            return msg.fail();
        msg = msg.success();
        msg.add("result",pageClass);
        msg.add("pageCount",pageCount);*/
        return msg;
    }
}
