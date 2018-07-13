package com.hubu.service;

import com.hubu.dao.LessonDAO;
import com.hubu.pojo.Lesson;
import com.hubu.pojo.Msg;
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

    public List<Lesson> getPageLesson(int currentPage) {
        try {
            return lessonDAO.selectPageLesson((currentPage-1)*pageCount,pageCount);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Lesson> getPageLessonByKeyWord(int currentPage, String keyWord) {
        try {
            return lessonDAO.selectPageLessonByKeyWord((currentPage-1)*pageCount,pageCount,keyWord);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
