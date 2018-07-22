package com.hubu.controller;

import com.github.pagehelper.PageInfo;
import com.hubu.pojo.Achievement;
import com.hubu.pojo.Msg;
import com.hubu.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AchievementController {

    @Autowired
    AchievementService achievementService;

    @RequestMapping(value = "/getAchievement",method = RequestMethod.GET)
    public Msg getAchievement(
            @RequestParam(value = "account",required = false) String account,
            @RequestParam(value = "lessonId",required = false) Integer lessonId,
            @RequestParam(value = "classId",required = false) Integer classId,
            @RequestParam(value = "currentPage",required = false) Integer currentPage
    ){
        try {
            PageInfo<Achievement> achievements = achievementService.getAchievement(account,lessonId,classId,currentPage);
            return new Msg().success().add("result",achievements);
        }catch (Exception e){
            e.printStackTrace();
            return new Msg().fail();
        }
    }

}
