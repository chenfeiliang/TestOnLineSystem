package com.hubu.service;

import com.hubu.dao.QuestionDAO;
import com.hubu.pojo.Msg;
import com.hubu.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;
    public Integer addQuestion(Question question) {
        try {
            return questionDAO.insertQuestion(question);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public Integer deleteQuestion(Integer questionId) {
        try {
            return questionDAO.deleteQuestion(questionId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public Integer updateQuestion(Question question) {
        try {
            return questionDAO.updateQuestion(question);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public List<Question> getPageQuestion(Integer currentPage) {
        try {
            return questionDAO.getPageQuestion(currentPage);
        }catch (Exception e){
            return null;
        }
    }
}
