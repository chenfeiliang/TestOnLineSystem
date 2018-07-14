package com.hubu.service;

import com.hubu.dao.LessonDAO;
import com.hubu.dao.PaperDao;
import com.hubu.dao.QuestionDAO;
import com.hubu.pojo.Msg;
import com.hubu.pojo.Paper;
import com.hubu.pojo.Question;
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



    public Integer addPaperByRandom(String title, Integer level, Integer lessonId) {
        StringBuilder ids = new StringBuilder();
        StringBuilder keys = new StringBuilder();
        List<Question> questions = new LinkedList<>();
        try {
            switch (level){
                case 1 :
                    return generatePaper(questions,ids,keys,title,lessonId,6,3,1);
                case 2 :
                    return generatePaper(questions,ids,keys,title,lessonId,3,6,1);
                case 3 :
                    return generatePaper(questions,ids,keys,title,lessonId,1,3,8);
                default:
                    return -1;
            }

        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    private Integer generatePaper(List<Question> questions,StringBuilder ids,StringBuilder keys,String title,Integer lessonId,Integer level1Count,Integer level2Count,Integer level3Count){
        questions.addAll(questionDAO.selectQuestionByLevelRandom(1,level1Count));
        questions.addAll(questionDAO.selectQuestionByLevelRandom(2,level2Count));
        questions.addAll(questionDAO.selectQuestionByLevelRandom(3,level3Count));
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
        paper.setKeys(keys.toString());
        paper.setCreater("random");
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

    public List<Paper> findPagePaper(int currentPage) {
        try {
            return paperDao.selectPagePaper((currentPage-1)*pageCount,pageCount);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Paper> getPagePaperByKeyWord(int currentPage, String keyword) {
        try {
            return paperDao.selectPagePaperByKeyWord((currentPage-1)*pageCount,pageCount,keyword);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
