package com.hubu.dao;

import com.hubu.pojo.Paper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaperDao {
    String TABLE_NAME = " paper ";
    String INSERT_FIELDS = " title,lessonId,questionIds,answer,creator ";
    String SELECT_FIELDS = " paperId,title,lessonId,questionIds,answer,creator ";

    @Insert({"insert into ",TABLE_NAME,"(",INSERT_FIELDS,") values (#{title}, #{lessonId},#{questionIds},#{answer},#{creator})"})
    Integer insertPaperByMan(Paper paper);

    @Delete({"delete from",TABLE_NAME,"where paperId = #{paperId}"})
    Integer deletePaper(Integer paperId);

    @Update({"update",TABLE_NAME,"set title=#{title},lessonId=#{lessonId},questionIds=#{questionIds},answer=#{answer},creator=#{creator} where paperId=#{paperId}"})
    Integer updatePaper(Paper paper);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME," order by paperId desc "})
    @Results({
            @Result(id = true,column = "paperId",property = "paperId"),
            @Result(column = "title",property = "title"),
            @Result(column = "lessonId",property = "lesson",
                    one = @One(select = "com.hubu.dao.LessonDAO.selectLessonIdByName")
            ),
            @Result(column = "questionIds",property = "questionIds"),
            @Result(column = "answer",property = "answer"),
            @Result(column = "creator",property = "creator")
    })
    List<Paper> selectPagePaper();

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME," where title like '%${keyword}%' order by paperId desc"})
    @Results({
            @Result(id = true,column = "paperId",property = "paperId"),
            @Result(column = "title",property = "title"),
            @Result(column = "lessonId",property = "lesson",
                    one = @One(select = "com.hubu.dao.LessonDAO.selectLessonIdByName")
            ),
            @Result(column = "questionIds",property = "questionIds"),
            @Result(column = "answer",property = "answer"),
            @Result(column = "creator",property = "creator")
    })
    List<Paper> selectPagePaperByKeyWord(@Param("keyword") String keyword);


    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where paperId = #{paperId}"})
    @Results({
            @Result(id = true,column = "paperId",property = "paperId"),
            @Result(column = "title",property = "title"),
            @Result(column = "lessonId",property = "lesson",
                    one = @One(select = "com.hubu.dao.LessonDAO.selectLessonIdByName")
            ),
            @Result(column = "questionIds",property = "questionIds"),
            @Result(column = "answer",property = "answer"),
            @Result(column = "creator",property = "creator")
    })
    Paper selectPaperById(Integer paperId);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME})
    @Results({
            @Result(id = true,column = "paperId",property = "paperId"),
            @Result(column = "title",property = "title"),
            @Result(column = "lessonId",property = "lesson",
                    one = @One(select = "com.hubu.dao.LessonDAO.selectLessonIdByName")
            ),
            @Result(column = "questionIds",property = "questionIds"),
            @Result(column = "answer",property = "answer"),
            @Result(column = "creator",property = "creator")
    })
    List<Paper> selectAllPaper();

    @Delete({"delete from",TABLE_NAME,"where paperId in (${paperIds})"})
    Integer batchDeletePaperById(@Param("paperIds") String paperIds);
}

