package com.hubu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hubu.dao.LessonDAO;
import com.hubu.pojo.Lesson;
import com.hubu.utils.Myutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
    @Autowired
    LessonDAO lessonDAO;
    private int pageCount = 10;
    public Integer addLesson(Lesson lesson) {
        try {
            return lessonDAO.addLesson(lesson);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public Integer deleteLesson(Integer lessonId) {
        try {
            return lessonDAO.deleteLesson(lessonId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public Integer updateLesson(Lesson lesson) {
        try {
            return lessonDAO.updateLesson(lesson);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public PageInfo<Lesson> getPageLesson(int currentPage) {
        try {
            PageHelper.startPage(currentPage, 10);
            List<Lesson> lessons = lessonDAO.selectLesson();
            PageInfo<Lesson> pageLesson = new PageInfo<>(lessons);
            int[] nums = pageLesson.getNavigatepageNums();
            int[] result = Myutils.pageCount(currentPage, nums);
            pageLesson.setNavigatepageNums(result);
            return pageLesson;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public PageInfo<Lesson> getPageLessonByKeyWord(int currentPage, String keyword) {
        try {
            PageHelper.startPage(currentPage, 10);
            List<Lesson> lessons = lessonDAO.selectLessonByKeyWord(keyword);
            PageInfo<Lesson> pageLesson = new PageInfo<>(lessons);
            int[] nums = pageLesson.getNavigatepageNums();
            int[] result = Myutils.pageCount(currentPage, nums);
            pageLesson.setNavigatepageNums(result);
            return pageLesson;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
