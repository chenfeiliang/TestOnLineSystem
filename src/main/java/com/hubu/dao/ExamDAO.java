package com.hubu.dao;

import com.hubu.pojo.Examin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExamDAO {

    String TABLE_NAME = " examin ";
    String INSERT_FIELDS =" title,paperId,lessonId,accounts,beginTime,endTime,type ";

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values(#{title},#{paperId},#{lessonId},#{accounts},#{beginTime},#{endTime},#{type})"})
    Integer insertExam(Examin examin);

    @Delete({"delete from",TABLE_NAME,"where examinId = #{examinId}"})
    Integer deleteExam(@Param("examinId") Integer examId);


}
