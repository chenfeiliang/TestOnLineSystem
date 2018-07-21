package com.hubu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hubu.dao.AchievementDAO;
import com.hubu.pojo.Achievement;
import com.hubu.utils.Myutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementService {

    @Autowired
    AchievementDAO achievementDAO;

    public PageInfo<Achievement> getAchievement(String account, Integer lessonId, Integer classId,Integer currentPage) throws Exception{
        if (lessonId == null)
            lessonId = 0;
        if (classId == null)
            classId = 0;
        if (currentPage == null)
            currentPage = 1;
        PageHelper.startPage(currentPage,10);
        List<Achievement> achievements = achievementDAO.selectAchievement(account, lessonId, classId);
        PageInfo<Achievement> achievementPageInfo = new PageInfo<>(achievements);
        int[] navigatepageNums = achievementPageInfo.getNavigatepageNums();
        int[] result = Myutils.pageCount(currentPage, navigatepageNums);
        achievementPageInfo.setNavigatepageNums(result);
        return achievementPageInfo;

    }
}
