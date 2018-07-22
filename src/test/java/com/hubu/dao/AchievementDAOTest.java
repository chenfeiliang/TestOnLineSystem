package com.hubu.dao;

import com.hubu.pojo.Achievement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AchievementDAOTest {

    @Autowired
    AchievementDAO achievementDAO;

    @Test
    public void selectAchievementByAccountAndExaminId() {
        List<Achievement> achievements = achievementDAO.selectAchievementByAccountAndExaminId("jiao");
        System.out.println(achievements.size());
    }
}