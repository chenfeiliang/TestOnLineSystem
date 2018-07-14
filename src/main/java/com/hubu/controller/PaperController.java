package com.hubu.controller;

import com.hubu.pojo.Msg;
import com.hubu.pojo.Paper;
import com.hubu.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
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
    @ResponseBody
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
    @RequestMapping(path = "/addPaperByRandom",method = {RequestMethod.GET})
    @ResponseBody
    public Msg addPaperByRandom(
                                @RequestParam("title") String title,
                                @RequestParam("lessonId") Integer lessonId,
                                @RequestParam("level") Integer level
    ){
        return paperService.addPaperByRandom(title,level,lessonId) == 1 ? new Msg().success() : new Msg().fail();
    }

    /*
     * 输入：试卷Id
     * 操作：删除试卷信息
     * 输出：操作结果
     * */
    public Msg deletePaper(Integer paperId){
        return paperService.deletePaper(paperId) == 1 ? new Msg().success() : new Msg().fail();
    }

    /*
     * 输入：试卷信息
     * 操作：更新试卷信息
     * 输出：操作结果
     * */
    public Msg updatePaper(Paper paper){
        return paperService.updatePaper(paper) == 1 ? new Msg().success() : new Msg().fail();
    }


    /*
     * 输入：页码
     * 操作：分页查询试卷信息
     * 输出：题目列表
     * */
    public Msg getPagePaper(int currentPage){
        List<Paper> papers = paperService.findPagePaper(currentPage);
        return papers == null ? new Msg().fail() : new Msg().success().add("result",papers);
    }

    /*
     * 输入：页码，关键词
     * 操作：通过关键词分页查询试卷信息
     * 输出：试卷列表
     * */
    public Msg getPagePaperByKeyWord(int currentPage,String keyword){
        List<Paper> papers = paperService.getPagePaperByKeyWord(currentPage,keyword);
        return papers == null ? new Msg().fail() : new Msg().success().add("result",papers);
    }

}
