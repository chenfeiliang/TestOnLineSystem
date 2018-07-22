package com.hubu.dao;

import com.hubu.pojo.Achievement;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AchievementDAO {

    String INSERT_FIELDS = " account,examinId,lessonId,classId,score ";
    String SELECT_FIELDS = " achievementId, account,examinId,lessonId,classId,score ";

    String TABLE_NAME = " achievement ";

    @Insert({"insert into ",TABLE_NAME,"(",INSERT_FIELDS,") values(#{account},#{examinId},#{lessonId},#{classId},#{score})"})
    Integer insertAchievement(Achievement achievement);

    List<Achievement> selectAchievement(@Param("account") String account,@Param("lessonId") Integer lessonId,@Param("classId") Integer classId);

    @Select("select"+SELECT_FIELDS+"from" +TABLE_NAME+"where account = #{account}")
    @Results({
            @Result(id = true,column = "achievementId",property = "achievementId"),
            @Result(column = "account",property = "account"),
            @Result(column = "examinId",property = "examinId"),
            @Result(column = "lessonId",property = "lessonId"),
            @Result(column = "classId",property = "classId"),
            @Result(column = "score",property = "score")
    })
    List<Achievement> selectAchievementByAccountAndExaminId(@Param("account") String account);

}
