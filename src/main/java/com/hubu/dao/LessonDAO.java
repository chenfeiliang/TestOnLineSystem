package com.hubu.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface LessonDAO {
    String TABLE_NAME = "lesson";
    @Select("select lessonName from" + TABLE_NAME + "where lessonId = #{lessonId}")
    String selectLessonIdByName(Integer lessonId);
}
