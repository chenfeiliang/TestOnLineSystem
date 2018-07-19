package com.hubu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hubu.dao.LessonDAO;
import com.hubu.dao.QuestionDAO;
import com.hubu.pojo.Lesson;
import com.hubu.pojo.Msg;
import com.hubu.pojo.Question;
import com.hubu.utils.Myutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;
    @Autowired
    LessonDAO lessonDAO;

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

    public PageInfo<Question> getPageQuestion(int currentPage) {
        try {
            PageHelper.startPage(currentPage, 10);
            List<Question> myQuestions = questionDAO.selectQuestion();
            PageInfo<Question> pageQuestion = new PageInfo<>(myQuestions);
            int[] nums = pageQuestion.getNavigatepageNums();
            int[] result = Myutils.pageCount(currentPage, nums);
            pageQuestion.setNavigatepageNums(result);
            return pageQuestion;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public PageInfo<Question> getPageQuestionByKeyWord(int currentPage, String keyword) {
        try {
            PageHelper.startPage(currentPage, 10);
            List<Question> myQuestions = questionDAO.selectQuestionByKeyWord(keyword);
            PageInfo<Question> pageQuestion = new PageInfo<>(myQuestions);
            int[] nums = pageQuestion.getNavigatepageNums();
            int[] result = Myutils.pageCount(currentPage, nums);
            pageQuestion.setNavigatepageNums(result);
            return pageQuestion;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Question getQuestionByQuestionId(Integer questionId) {
        try {
            return questionDAO.selectQuestionIdByName(questionId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Integer batchDeleteQuestionById(String questionIds) {
        try {
            return questionDAO.batchDeleteQuestionById(questionIds);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Lesson> getAllLesson() {
        try {
            return lessonDAO.selectLesson();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
