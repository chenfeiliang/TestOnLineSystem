package com.hubu.controller;

import com.hubu.pojo.Lesson;
import com.hubu.pojo.Msg;
import com.hubu.service.LessonService;
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
        List<Lesson> pageLesson = lessonService.getPageLesson(currentPage);
        return pageLesson == null ? new Msg().fail() : new Msg().success().add("result",pageLesson);
    }

    @RequestMapping(value = "/getPageLessonByKeyWord",method = {RequestMethod.GET})
    @ResponseBody
    public Msg getPageLessonByKeyWord (Integer currentPage,String keyword){
        List<Lesson> pageLessonByKeyWord = lessonService.getPageLessonByKeyWord(currentPage, keyword);
        return pageLessonByKeyWord == null ? new Msg().fail() : new Msg().success().add("result",pageLessonByKeyWord);
    }
}
