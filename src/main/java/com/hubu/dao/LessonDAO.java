package com.hubu.dao;

import com.hubu.pojo.Lesson;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LessonDAO {
    String TABLE_NAME = " lesson ";
    String INSERT_FIELDS = " lessonName ";
    String SELECT_FIELDS = " lessonId,lessonName ";

    @Select({"select",SELECT_FIELDS,"from" + TABLE_NAME + "where lessonId = #{lessonId}"})
    @Results({
            @Result(id = true,property = "lessonId",column = "lessonId"),
            @Result(property = "lessonName",column = "lessonName")
    })
    String selectLessonIdByName(Integer lessonId);

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values(#{lessonName})"})
    Integer addLesson(Lesson lesson);

    @Delete("delete from" + TABLE_NAME + "where lessonId=#{lessonId}")
    int deleteLesson(int lessonId);

    @Update("update" + TABLE_NAME + "set lessonName=#{lessonName} where lessonId = #{lessonId}")
    int updateLesson(Lesson lesson);

    @Select({"select" + SELECT_FIELDS + "from" + TABLE_NAME,"limit #{currentPage},#{pageCount}"})
    @Results({
            @Result(id = true,property = "lessonId",column = "lessonId"),
            @Result(property = "lessonName",column = "lessonName")
    })
    List<Lesson> selectPageLesson(@Param("currentPage") int currentPage, @Param("pageCount") Integer pageCount);

    @Select({"select" + SELECT_FIELDS + "from" + TABLE_NAME + "where lessonName like '%${keyWord}%'","limit #{currentPage},#{pageCount}"})
    @Results({
            @Result(id = true,property = "lessonId",column = "lessonId"),
            @Result(property = "lessonName",column = "lessonName")
    })
    List<Lesson> selectPageLessonByKeyWord(@Param("currentPage") int currentPage,@Param("pageCount") Integer pageCount,@Param("keyWord") String keyWord);
}

