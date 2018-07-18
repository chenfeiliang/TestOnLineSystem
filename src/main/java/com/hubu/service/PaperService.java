package com.hubu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hubu.dao.LessonDAO;
import com.hubu.dao.PaperDao;
import com.hubu.dao.QuestionDAO;
import com.hubu.pojo.Msg;
import com.hubu.pojo.Paper;
import com.hubu.pojo.Question;
import com.hubu.utils.Myutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PaperService {
    @Autowired
    PaperDao paperDao;
    @Autowired
    QuestionDAO questionDAO;
    @Autowired
    LessonDAO lessonDAO;

    private int pageCount = 10;

    public Integer addPaperByMan(Paper paper) {
        try {
            return paperDao.insertPaperByMan(paper);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }



    public Integer addPaperByRandom(String title, Integer lessonId,Integer level1Count,Integer level2Count,Integer level3Count) {
        StringBuilder ids = new StringBuilder();
        StringBuilder keys = new StringBuilder();
        List<Question> questions = new LinkedList<>();
        try {
            return generatePaper(questions,ids,keys,title,lessonId,level1Count,level2Count,level3Count);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    private Integer generatePaper(List<Question> questions,StringBuilder ids,StringBuilder keys,String title,Integer lessonId,Integer level1Count,Integer level2Count,Integer level3Count){
        questions.addAll(questionDAO.selectQuestionByLevelRandom(1,level1Count,lessonId));
        questions.addAll(questionDAO.selectQuestionByLevelRandom(2,level2Count,lessonId));
        questions.addAll(questionDAO.selectQuestionByLevelRandom(3,level3Count,lessonId));
        for (Question question : questions){
            ids.append(question.getQuestionId() + ",");
            keys.append(question.getQuestionKey() + ",");
        }
        ids.setLength(ids.length()-1);
        keys.setLength(keys.length()-1);
        Paper paper = new Paper();
        paper.setTitle(title);
        paper.setLessonId(lessonId);
        paper.setQuestionIds(ids.toString());
        paper.setAnswer(keys.toString());
        paper.setCreator("random");
        return paperDao.insertPaperByMan(paper);
    }

    public Integer deletePaper(Integer paperId) {
        try {
            return paperDao.deletePaper(paperId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public Integer updatePaper(Paper paper) {
        try {
            return paperDao.updatePaper(paper);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public PageInfo<Paper> getPagePaper(int currentPage) {
        try {
            PageHelper.startPage(currentPage,10);
            List<Paper> papers = paperDao.selectPagePaper();
            PageInfo<Paper> paperPageInfo = new PageInfo<>(papers);
            int[] nums = paperPageInfo.getNavigatepageNums();
            int[] result = Myutils.pageCount(currentPage, nums);
            paperPageInfo.setNavigatepageNums(result);
            return paperPageInfo;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public PageInfo<Paper> getPagePaperByKeyWord(int currentPage, String keyword) {
        try {
            PageHelper.startPage(currentPage,10);
            List<Paper> papers = paperDao.selectPagePaperByKeyWord(keyword);
            PageInfo<Paper> paperPageInfo = new PageInfo<>(papers);
            int[] nums = paperPageInfo.getNavigatepageNums();
            int[] result = Myutils.pageCount(currentPage, nums);
            paperPageInfo.setNavigatepageNums(result);
            return paperPageInfo;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Paper getPaperById(Integer paperId) {
        try {
            Paper paper = paperDao.selectPaperById(paperId);
            List<Question> questions = questionDAO.selectQuestionByIds(paper.getQuestionIds());
            paper.setQuestions(questions);
            return paper;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Integer batchDeletePaperById(String paperIds) {
        try {
            return paperDao.batchDeletePaperById(paperIds);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
