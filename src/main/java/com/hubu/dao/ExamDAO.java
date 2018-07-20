package com.hubu.dao;

import com.hubu.pojo.Examin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExamDAO {

    String TABLE_NAME = " examin ";
    String INSERT_FIELDS = " title,paperId,lessonId,accounts,beginTime,endTime,type ";
    String SELECT_FIELDS = " examinId,title,paperId,lessonId,accounts,beginTime,endTime,type ";

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values(#{title},#{paperId},#{lessonId},#{accounts},#{beginTime},#{endTime},#{type})"})
    Integer insertExam(Examin examin);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"order by examinId desc"})
    @Results({
            @Result(id = true,column = "examinId",property = "examinId"),
            @Result(column = "title",property = "title"),
            @Result(column = "paperId",property = "paper",
                    one = @One(select = "com.hubu.dao.PaperDao.selectPaperById")
            ),
            @Result(column = "lessonId",property = "lesson",
                    one = @One(select = "com.hubu.dao.LessonDAO.selectLessonIdByName")
            ),
            @Result(column = "accounts",property = "accounts"),
            @Result(column = "beginTime",property = "beginTime"),
            @Result(column = "endTime",property = "endTime"),
            @Result(column = "type",property = "type"),
    })
    List<Examin> selectExam();

    @Delete({"delete from",TABLE_NAME,"where examinId in (${examinId})"})
    Integer deleteExam(@Param("examinId") String examId);

    @Update({"update",TABLE_NAME,"set title=#{title},paperId=#{paperId},lessonId=#{lessonId},accounts=#{accounts},beginTime=#{beginTime},endTime=#{endTime},type=#{type} where examinId = #{examinId}"})
    Integer updateExam(Examin examin);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME," where title like '%${keyword}%' order by examinId desc"})
    @Results({
            @Result(id = true,column = "examinId",property = "examinId"),
            @Result(column = "title",property = "title"),
            @Result(column = "paperId",property = "paper",
                    one = @One(select = "com.hubu.dao.PaperDao.selectPaperById")
            ),
            @Result(column = "lessonId",property = "lesson",
                    one = @One(select = "com.hubu.dao.LessonDAO.selectLessonIdByName")
            ),
            @Result(column = "accounts",property = "accounts"),
            @Result(column = "beginTime",property = "beginTime"),
            @Result(column = "endTime",property = "endTime"),
            @Result(column = "type",property = "type"),
    })
    List<Examin> selectExamByKeyWord(@Param("keyword") String keyword);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where examinId = #{examinId}"})
    @Results({
            @Result(id = true,column = "examinId",property = "examinId"),
            @Result(column = "title",property = "title"),
            @Result(column = "paperId",property = "paper",
                    one = @One(select = "com.hubu.dao.PaperDao.selectPaperById")
            ),
            @Result(column = "lessonId",property = "lesson",
                    one = @One(select = "com.hubu.dao.LessonDAO.selectLessonIdByName")
            ),
            @Result(column = "accounts",property = "accounts"),
            @Result(column = "beginTime",property = "beginTime"),
            @Result(column = "endTime",property = "endTime"),
            @Result(column = "type",property = "type"),
    })
    Examin selectExamByExaminId(Integer examinId);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where lessonId = #{lessonId}"})
    @Results({
            @Result(id = true,column = "examinId",property = "examinId"),
            @Result(column = "title",property = "title"),
            @Result(column = "paperId",property = "paper",
                    one = @One(select = "com.hubu.dao.PaperDao.selectPaperById")
            ),
            @Result(column = "lessonId",property = "lesson",
                    one = @One(select = "com.hubu.dao.LessonDAO.selectLessonIdByName")
            ),
            @Result(column = "accounts",property = "accounts"),
            @Result(column = "beginTime",property = "beginTime"),
            @Result(column = "endTime",property = "endTime"),
            @Result(column = "type",property = "type"),
    })
    List<Examin> getExamByLessonId(@Param("lessonId") Integer lessonId);
}
