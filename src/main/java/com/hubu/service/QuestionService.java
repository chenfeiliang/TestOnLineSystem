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

    private int pageCount = 10;

    public Integer addQuestion(Question question) {
        try {
            Integer integer = questionDAO.insertQuestion(question);
            return integer;
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

    public List<Question> getPageQuestion(int currentPage) {
        try {
            return questionDAO.selectPageQuestion((currentPage-1)*pageCount,pageCount);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Question> getPageQuestionByKeyWord(int currentPage, String keyword) {
        try {
            return questionDAO.selectPageQuestionByKeyWord((currentPage-1)*pageCount,pageCount,keyword);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
