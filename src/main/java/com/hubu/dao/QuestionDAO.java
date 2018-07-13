package com.hubu.dao;

import com.hubu.pojo.Question;
import org.apache.ibatis.annotations.*;

@Mapper
public interface QuestionDAO {
    String TABLE_NAME = " question ";
    String INSERT_FIELDS = " title,optionA,optionB,optionC,optionD,questionKey,questionLevel,lessonId,creator ";

    @Insert("insert into" + TABLE_NAME + "(" + INSERT_FIELDS +") values(#{title},#{optionA},#{optionB},#{optionC},#{optionD},#{questionKey},#{questionLevel},#{lessonId},#{creator})")
    @Results({
            @Result(id = true,property = "title",column = "title"),
            @Result(property = "optionA",column = "optionA"),
            @Result(property = "optionB",column = "optionB"),
            @Result(property = "optionC",column = "optionC"),
            @Result(property = "optionD",column = "optionD"),
            @Result(property = "questionKey",column = "questionKey"),
            @Result(property = "questionLevel",column = "questionLevel"),
            @Result(property = "lessonId",column = "lessonId"
//                    ,one = @One(select = "com.hubu.dao.LessonDAO.selectLessonIdByName")
            ),
            @Result(property = "creator",column = "creator"),
    })
    Integer insertQuestion(Question question);
}
