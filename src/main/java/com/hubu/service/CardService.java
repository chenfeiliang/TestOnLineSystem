package com.hubu.service;

import com.hubu.dao.*;
import com.hubu.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardService {

    @Autowired
    CardDAO cardDAO;

    @Autowired
    PaperDao paperDao;

    @Autowired
    UserDAO userDAO;

    @Autowired
    AchievementDAO achievementDAO;

    @Autowired
    WrongDAO wrongDAO;

    @Transactional
    public Integer addCard(Card card) {
        try {
            int flag = cardDAO.isExit(card.getExaminId(),card.getAccount());
            if(flag<=0){
                Paper paper = paperDao.selectPaperById(card.getPaperId());
                String[] key = paper.getAnswer().split(",");
                String[] questionIds = paper.getQuestionIds().split(",");
                String[] cardAnwser = card.getOptions().split(",");
                Integer point = 0;
                StringBuilder errQuestionIds = new StringBuilder();
                StringBuilder err = new StringBuilder();
                for (int i = 0; i < key.length; i++) {
                    if (key[i].equals(cardAnwser[i]))
                        ++point;
                    else if (cardAnwser[i].equals("-1"))
                        continue;
                    else{
                        errQuestionIds.append(questionIds[i] + ",");
                        err.append(cardAnwser[i] + ",");
                    }
                }
                errQuestionIds.setLength(errQuestionIds.length()-1);
                err.setLength(err.length()-1);
                Achievement achievement = new Achievement();
                achievement.setAccount(card.getAccount());
                achievement.setClassId(userDAO.selectUserByUserId(card.getAccount()).getMyClass().getClassId());
                achievement.setExaminId(card.getExaminId());
                achievement.setLessonId(paper.getLesson().getLessonId());
                achievement.setScore(point);
                if (achievementDAO.insertAchievement(achievement) != 1)
                    throw new RuntimeException();
                Wrong wrong = new Wrong();
                wrong.setExaminId(card.getExaminId());
                wrong.setPaperId(card.getPaperId());
                wrong.setQuestionIds(errQuestionIds.toString());
                wrong.setAccount(card.getAccount());
                wrong.setOptions(err.toString());
                if (wrongDAO.insertWrong(wrong) != 1)
                    throw new RuntimeException();
                if (cardDAO.insertCard(card) != 1)
                    throw new RuntimeException();
                return 1;




            }else{
                return cardDAO.updateCard(card.getExaminId(),card.getAccount(),card.getOptions());
            }

        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
