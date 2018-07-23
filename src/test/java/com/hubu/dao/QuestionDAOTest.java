package com.hubu.dao;

import com.hubu.pojo.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionDAOTest {

    @Autowired
    QuestionDAO questionDAO;

    @Test
    public void insertQuestion() {
        for (int i = 1 ; i<=100;i++){
            for (int j = 1 ; j<=100 ; j++){
                Random rand = new Random();
                Question question = new Question();
                question.setTitle(" "+ i+" * " + j + "  = ( ) " );
                question.setOptionA(""+(rand.nextInt(i*j)));
                question.setOptionB(""+(rand.nextInt(i*j)));
                question.setOptionC(""+(rand.nextInt(i*j)));
                question.setOptionD(""+(i*j));
                question.setQuestionKey("d");
                question.setQuestionLevel("容易");
                question.setLessonId(4501);
                questionDAO.insertQuestion(question);
            }
        }
    }
}