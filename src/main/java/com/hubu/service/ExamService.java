package com.hubu.service;

import com.hubu.dao.ExamDAO;
import com.hubu.pojo.Examin;
import com.hubu.pojo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {

    @Autowired
    ExamDAO examDAO;

    public Integer addExam(Examin examin) {
        try {
            return examDAO.insertExam(examin);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public Integer deleteExam(Integer examId) {
        try {
            return examDAO.deleteExam(examId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
