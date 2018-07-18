package com.hubu.controller;

import com.github.pagehelper.PageInfo;
import com.hubu.pojo.Msg;
import com.hubu.pojo.Paper;
import com.hubu.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class PaperController {
    @Autowired
    PaperService paperService;
    /*
     *
     * 输入：一组题目id
     * 操作：手动选择题目，生成，并添加试卷信息
     * 输出：操作结果
     * */
    @RequestMapping(path = "/addPaperByMan",method = {RequestMethod.POST})
    public Msg addPaperByMan(Paper paper,
                             HttpServletRequest request,
                             HttpServletRequest resRequest
    ){
        return paperService.addPaperByMan(paper) == 1 ? new Msg().success() : new Msg().fail();
    }

    /*
     *
     * 输入：
     * 操作：随机抽取题目，生成，并添加试卷信息
     * 输出：操作结果
     * */
    @RequestMapping(path = "/addPaperByRandom", method = {RequestMethod.POST})
    public Msg addPaperByRandom(
                                @RequestParam("title") String title,
                                @RequestParam("lessonId") Integer lessonId,
                                @RequestParam("level1Count") Integer level1Count,
                                @RequestParam("level2Count") Integer level2Count,
                                @RequestParam("level3Count") Integer level3Count
    ){
        return paperService.addPaperByRandom(title,lessonId,level1Count,level2Count,level3Count) == 1 ? new Msg().success() : new Msg().fail();
    }

    /*
     * 输入：试卷Id
     * 操作：删除试卷信息
     * 输出：操作结果
     * */
    @RequestMapping(path = "/deletePaper", method = {RequestMethod.GET})
    public Msg deletePaper(Integer paperId){
        return paperService.deletePaper(paperId) == 1 ? new Msg().success() : new Msg().fail();
    }

    /*
     * 输入：试卷信息
     * 操作：更新试卷信息
     * 输出：操作结果
     * */
    @RequestMapping(path = "/updatePaper",method = {RequestMethod.POST})
    public Msg updatePaper(Paper paper){
        return paperService.updatePaper(paper) == 1 ? new Msg().success() : new Msg().fail();
    }


    /*
     * 输入：页码
     * 操作：分页查询试卷信息
     * 输出：题目列表
     * */
    @RequestMapping(path = "/getPagePaper",method = {RequestMethod.GET})
    public Msg getPagePaper(int currentPage){
        PageInfo<Paper> papers = paperService.getPagePaper(currentPage);
        return papers == null ? new Msg().fail() : new Msg().success().add("result",papers);
    }

    /*
     * 输入：页码，关键词
     * 操作：通过关键词分页查询试卷信息
     * 输出：试卷列表
     * */
    @RequestMapping(path = "/getPagePaperByKeyWord",method = {RequestMethod.GET})
    public Msg getPagePaperByKeyWord(int currentPage,String keyword){
        PageInfo<Paper> papers = paperService.getPagePaperByKeyWord(currentPage,keyword);
        return papers == null ? new Msg().fail() : new Msg().success().add("result",papers);
    }

    @RequestMapping(path = "/getPaperById",method = {RequestMethod.GET})
    public Msg getPaperById(Integer paperId){
        Paper paper = paperService.getPaperById(paperId);
        return paper == null ? new Msg().fail() : new Msg().success().add("result",paper);
    }
    @RequestMapping(path = "/batchDeletePaperById",method = {RequestMethod.GET})
    public Msg batchDeletePaperById(String paperIds){
        return paperService.batchDeletePaperById(paperIds) > 0 ? new Msg().success() : new Msg().fail();
    }
}

