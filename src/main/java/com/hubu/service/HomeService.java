package com.hubu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hubu.dao.*;
import com.hubu.dto.QuestionDTO;
import com.hubu.pojo.*;
import com.hubu.utils.DateUtil;
import com.hubu.utils.Myutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HomeService {

    @Autowired
    ExamDAO examDAO;

    @Autowired
    QuestionDAO questionDAO;

    @Autowired
    CardDAO cardDAO;

    @Autowired
    WrongDAO wrongDAO;

    @Autowired
    AchievementDAO achievementDAO;


    public List<Map> getExamByAccount(String accunt){

        Map<String,Object> map = null;
        List<Map> results = new ArrayList<>();
        List<Examin> examinList = new ArrayList<>();
        try {
            List<Examin> examins = examDAO.selectExam();
            for (Examin examin:examins) {

                String [] accounts = examin.getAccounts().split(",");

                map = new HashMap<>();

                for (String accountTemp :  accounts)
                {
                    if(accunt.equals(accountTemp))
                    {
                        map.put("examinId",examin.getExaminId());
                        map.put("title",examin.getTitle());
                        map.put("type",examin.getType());
                        map.put("beginTime", DateUtil.DateToString(examin.getBeginTime()));
                        map.put("endTime",DateUtil.DateToString(examin.getEndTime()));
                        map.put("paperTitle",examin.getPaper().getTitle());
                        results.add(map);
                    }
                }
            }

            return results;
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public  List<Map> getScore(String account){
        System.out.println(account);
        try{
            Map<String,Object> map = null;
            List<Map> results = new ArrayList<>();

            List<Achievement> achievements =  achievementDAO.selectAchievementByAccountAndExaminId(account);

            if (achievements!=null){
                for (Achievement achievement : achievements){
                    map = new HashMap<>();
                    Examin examin  = examDAO.selectExamByExaminId(achievement.getExaminId());
                    map.put("title",examin.getTitle());
                    map.put("score",achievement.getScore());
                    map.put("beginTime", DateUtil.DateToString(examin.getBeginTime()));
                    map.put("endTime",DateUtil.DateToString(examin.getEndTime()));
                    results.add(map);
                }
            }
            return results;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }



    }

    public Msg getCardByExamId(Integer examinId, String account){
        try {

                int flag = cardDAO.isExit(examinId,account);

                if(flag<=0)
                {
                    Examin examin = examDAO.selectExamByExaminId(examinId);

                    Map<String,Object> map = new HashMap<>();

                    long time = DateUtil.calLastedTime(new Date(),examin.getEndTime());

                    if(time == 0 ){
                        return new Msg().fail().add("errorInfo","考试时间已过！");
                    }

                    Integer paperId= examin.getPaper().getPaperId();

                    List<Question> questions = questionDAO.selectQuestionByIds(examin.getPaper().getQuestionIds());

                    for ( Question question : questions){
                        question.setQuestionKey("");
                    }

                    map.put("paperId",paperId);

                    map.put("questions",questions);

                    map.put("title",examin.getTitle());

                    map.put("time",time);

                    return new Msg().success().add("result",map).add("user",account);
                }
                else
                {
                    return new Msg().fail().add("errorInfo","你已经参加过考试，请勿重复参加！");
                }

        }catch (Exception e){
            e.printStackTrace();
            return new Msg().fail().add("errorInfo","系统错误");
        }
    }


    public Msg getWrongExamIdAndAccount(Integer examinId, String account){
        try {

                Examin examin = examDAO.selectExamByExaminId(examinId);

                Map<String,Object> map = new HashMap<>();

                Integer paperId= examin.getPaper().getPaperId();

                List<Question> questions = questionDAO.selectQuestionByIds(examin.getPaper().getQuestionIds());

                List<QuestionDTO> questionDAOS = new ArrayList<>();

                Wrong wrong = wrongDAO.selectWrongByExaminIdAndAccount(examinId,account);
                System.out.println(wrong.getQuestionIds());
                String [] wrongQuestions = wrong.getQuestionIds().split(",");
                String [] options = wrong.getOptions().split(",");

                for(int i = 0 ;i<questions.size();i++){

                    QuestionDTO questionDTO = new QuestionDTO();
                    questionDTO.setQuestionId(questions.get(i).getQuestionId()) ;
                    questionDTO.setTitle(questions.get(i).getTitle());
                    questionDTO.setOptionA(questions.get(i).getOptionA());
                    questionDTO.setOptionB(questions.get(i).getOptionB());
                    questionDTO.setOptionC(questions.get(i).getOptionC());
                    questionDTO.setOptionD(questions.get(i).getOptionD());
                    questionDTO.setQuestionKey(questions.get(i).getQuestionKey());
                    questionDTO.setQuestionLevel(questions.get(i).getQuestionLevel());
                    questionDTO.setLessonId(questions.get(i).getLessonId());
                    questionDTO.setCreator(questions.get(i).getCreator());

                    int qid = questions.get(i).getQuestionId();
                    for(int j = 0 ;j<wrongQuestions.length;j++){
                        if(Integer.parseInt(wrongQuestions[j])==qid){
                            questionDTO.setIsWrong(1);
                            questionDTO.setWrongKey(options[j]);
                        }
                    }
                    questionDAOS.add(questionDTO);
                }

                map.put("questions",questionDAOS);

                map.put("title",examin.getTitle());

                return new Msg().success().add("result",map).add("user",account);

        }catch (Exception e){
            e.printStackTrace();
            return new Msg().fail().add("errorInfo","系统错误");
        }
    }




    public Map getExamTitleAndTimeById(Integer examinId,String account) {
        try {
            int flag = cardDAO.isExit(examinId,account);

            if(flag<=0){
                Examin examin = examDAO.selectExamByExaminId(examinId);

                Map<String,Object> map = new HashMap<>();

                map.put("title",examin.getTitle());

                long time = DateUtil.calLastedTime(new Date(),examin.getBeginTime());

                if (time<=0){
                    map.put("time",0);
                }
                else {
                    map.put("time",time);
                }
                return map;
            }else {
                return null;
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
