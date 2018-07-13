package com.hubu.dao;

import com.hubu.pojo.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionDAO {
    String TABLE_NAME = " question ";
    String INSERT_FIELDS = " questionId,title,optionA,optionB,optionC,optionD,questionKey,questionLevel,lessonId,creator ";
    String SELECT_FIELDS = " questionId,title,optionA,optionB,optionC,optionD,questionKey,questionLevel,lessonId,creator ";
    @Insert("insert into" + TABLE_NAME + "(" + INSERT_FIELDS +") values(#{title},#{optionA},#{optionB},#{optionC},#{optionD},#{questionKey},#{questionLevel},#{lessonId},#{creator})")
    Integer insertQuestion(Question question);

    @Delete({"delete from",TABLE_NAME,"where questionId = #{questionId}"})
    Integer deleteQuestion(Integer questionId);

    @Update({"update",TABLE_NAME,"set title=#{title},optionA=#{optionA},optionB=#{optionB},optionC=#{optionC},optionD=#{optionD},questionKey=#{questionKey},questionLevel=#{questionLevel},lessonId=#{lessonId},creator=#{creator} where questionId = #{questionId}"})
    Integer updateQuestion(Question question);

    @Select("select" + SELECT_FIELDS + "from" + TABLE_NAME + "limit #{currentPage},#{pageCount}")
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
    List<Question> getPageQuestion(Integer currentPage);
}
