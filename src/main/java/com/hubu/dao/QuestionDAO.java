package com.hubu.dao;

import com.hubu.pojo.Question;
import org.apache.ibatis.annotations.*;

import java.util.Collection;
import java.util.List;

@Mapper
public interface QuestionDAO {
    String TABLE_NAME = " question ";
    String INSERT_FIELDS = " title,optionA,optionB,optionC,optionD,questionKey,questionLevel,lessonId,creator ";
    String SELECT_FIELDS = " questionId,title,optionA,optionB,optionC,optionD,questionKey,questionLevel,lessonId,creator ";
    @Insert("insert into" + TABLE_NAME + "(" + INSERT_FIELDS +") values(#{title},#{optionA},#{optionB},#{optionC},#{optionD},#{questionKey},#{questionLevel},#{lessonId},#{creator})")
    Integer insertQuestion(Question question);

    @Delete({"delete from",TABLE_NAME,"where questionId = #{questionId}"})
    Integer deleteQuestion(Integer questionId);

    @Update({"update",TABLE_NAME,"set title=#{title},optionA=#{optionA},optionB=#{optionB},optionC=#{optionC},optionD=#{optionD},questionKey=#{questionKey},questionLevel=#{questionLevel},lessonId=#{lessonId},creator=#{creator} where questionId = #{questionId}"})
    Integer updateQuestion(Question question);

    @Select("select" + SELECT_FIELDS + "from" + TABLE_NAME + " order by questionId desc limit #{currentPage},#{pageCount}")
    @Results({
            @Result(id = true,column = "questionId",property = "questionId"),
            @Result(column = "title",property = "title"),
            @Result(column = "optionA",property = "optionA"),
            @Result(column = "optionB",property = "optionB"),
            @Result(column = "optionC",property = "optionC"),
            @Result(column = "optionD",property = "optionD"),
            @Result(column = "questionKey",property = "questionKey"),
            @Result(column = "questionLevel",property = "questionLevel"),
            @Result(property = "lesson",column = "lessonId"
                    , one = @One(select = "com.hubu.dao.LessonDAO.selectLessonIdByName")
            ),
            @Result(column = "creator",property = "creator")
    })
    List<Question> selectPageQuestion(@Param("currentPage") int currentPage,@Param("pageCount") int pageCount);

    @Select({"select" + SELECT_FIELDS + "from",TABLE_NAME,"where title like '%${keyword}%' order by questionId desc limit #{currentPage},#{pageCount}"})
    @Results({
            @Result(id = true,column = "questionId",property = "questionId"),
            @Result(column = "title",property = "title"),
            @Result(column = "optionA",property = "optionA"),
            @Result(column = "optionB",property = "optionB"),
            @Result(column = "optionC",property = "optionC"),
            @Result(column = "optionD",property = "optionD"),
            @Result(column = "questionKey",property = "questionKey"),
            @Result(column = "questionLevel",property = "questionLevel"),
            @Result(column = "lessonId",property = "lesson",
                    one = @One(select = "com.hubu.dao.LessonDAO.selectLessonIdByName")
            ),
            @Result(column = "creator",property = "creator")
    })
    List<Question> selectPageQuestionByKeyWord(@Param("currentPage") int currentPage,@Param("pageCount") int pageCount,@Param("keyword") String keyword);

    @Select({"select ",SELECT_FIELDS," from",TABLE_NAME,"where questionLevel = #{questionLevel} order by rand() limit #{count}"})
    @Results({
            @Result(id = true,column = "questionId",property = "questionId"),
            @Result(column = "title",property = "title"),
            @Result(column = "optionA",property = "optionA"),
            @Result(column = "optionB",property = "optionB"),
            @Result(column = "optionC",property = "optionC"),
            @Result(column = "optionD",property = "optionD"),
            @Result(column = "questionKey",property = "questionKey"),
            @Result(column = "questionLevel",property = "questionLevel"),
            @Result(column = "lessonId",property = "lesson",
                    one = @One(select = "com.hubu.dao.LessonDAO.selectLessonIdByName")
            ),
            @Result(column = "creator",property = "creator")
    })
    List<Question> selectQuestionByLevelRandom(Integer questionLevel, Integer count);
}
