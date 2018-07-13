package com.hubu.service;

import com.hubu.dao.LessonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
    @Autowired
    LessonDAO lessonDAO;
}
