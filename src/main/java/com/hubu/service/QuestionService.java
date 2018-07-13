package com.hubu.service;

import com.hubu.dao.QuestionDAO;
import com.hubu.pojo.Msg;
import com.hubu.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;
    public Msg addQuestion(Question question) {
//        try {
            return new Msg().success().add("result",questionDAO.insertQuestion(question));
//        }catch (Exception e){
//            return new Msg().fail();
//        }
    }
}
