package com.hubu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hubu.dao.*;
import com.hubu.pojo.Card;
import com.hubu.pojo.Examin;
import com.hubu.pojo.Paper;
import com.hubu.pojo.Question;
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

    public Map<String,Object> getCardByExamId(Integer examinId){
        try {
                Examin examin = examDAO.selectExamByExaminId(examinId);

                Map<String,Object> map = new HashMap<>();

                long time = DateUtil.calLastedTime(new Date(),examin.getEndTime());

                if(time == 0 ){
                    return null;
                }

                Integer paperId= examin.getPaper().getPaperId();

                List<Question> questions = questionDAO.selectQuestionByIds(examin.getPaper().getQuestionIds());

                map.put("paperId",paperId);

                map.put("questions",questions);

                map.put("title",examin.getTitle());

                map.put("time",time);


                return map;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Map getExamTitleAndTimeById(Integer examinId) {
        try {
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

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
