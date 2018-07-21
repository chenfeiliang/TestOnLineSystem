package com.hubu.dao;

import com.hubu.pojo.Achievement;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AchievementDAO {

    String INSERT_FIELDS = " account,examinId,lessonId,classId,score ";
    String TABLE_NAME = " achievement ";

    @Insert({"insert into ",TABLE_NAME,"(",INSERT_FIELDS,") values(#{account},#{examinId},#{lessonId},#{classId},#{score})"})
    Integer insertAchievement(Achievement achievement);

    List<Achievement> selectAchievement(@Param("account") String account,@Param("lessonId") Integer lessonId,@Param("classId") Integer classId);
}
