package com.hubu.controller;

import com.hubu.pojo.Msg;
import com.hubu.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LessonController {
    @Autowired
    LessonService lessonService;

}
