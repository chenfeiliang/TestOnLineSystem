package com.hubu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hubu.dao.ExamDAO;
import com.hubu.dao.UserDAO;
import com.hubu.pojo.Examin;
import com.hubu.pojo.Msg;
import com.hubu.pojo.User;
import com.hubu.utils.DateUtil;
import com.hubu.utils.Myutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamService {

    @Autowired
    ExamDAO examDAO;
    @Autowired
    UserDAO userDAO;

    public Integer addExam(Examin examin) {
        try {
            return examDAO.insertExam(examin);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public Integer deleteExam(String examIds) {
        try {
            return examDAO.deleteExam(examIds);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public Integer updateExam(Examin examin) {
        try {
            return examDAO.updateExam(examin);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public PageInfo<Examin> getPageExam(Integer currentPage) {
        try {
            PageHelper.startPage(currentPage, 10);
            List<Examin> examins = examDAO.selectExam();
            PageInfo<Examin> pageExamin = new PageInfo<>(examins);
            int[] nums = pageExamin.getNavigatepageNums();
            int[] result = Myutils.pageCount(currentPage, nums);
            pageExamin.setNavigatepageNums(result);
            return pageExamin;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public PageInfo<Examin> getPageExamByKeyWord(Integer currentPage, String keyword) {
        try {
            PageHelper.startPage(currentPage, 10);
            List<Examin> examins = examDAO.selectExamByKeyWord(keyword);
            PageInfo<Examin> pageExamin = new PageInfo<>(examins);
            int[] nums = pageExamin.getNavigatepageNums();
            int[] result = Myutils.pageCount(currentPage, nums);
            pageExamin.setNavigatepageNums(result);
            return pageExamin;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Examin getExamByExaminId(Integer examinId) {
        try {
            Examin examin = examDAO.selectExamByExaminId(examinId);
            String[] accounts = examin.getAccounts().split(",");
            for(String account : accounts){
                examin.getUsers().add(userDAO.selectUserByUserId(account));
            }
            return examin;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    public Map<String,Object> getExamByLessonIdAndAccount(Integer lessonId, String account) {
        try {
            List<Examin> examins = examDAO.getExamByLessonId(lessonId, account);
            for (Examin examin : examins){
                String[] split = examin.getAccounts().split(",");
                List<String> strings = Arrays.asList(split);
                if (strings.contains(account)){
                    Date beginTime = examin.getBeginTime();
                    Date rest = new Date();
                    rest.setTime(beginTime.getTime() - rest.getTime());
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("exam",examin);
                    map.put("rest",rest.getTime());
                    return map;
                }

            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
