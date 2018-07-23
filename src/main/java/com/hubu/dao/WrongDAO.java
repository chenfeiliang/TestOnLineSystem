package com.hubu.dao;

import com.hubu.pojo.Wrong;
import org.apache.ibatis.annotations.*;

@Mapper
public interface WrongDAO {

    String INSERT_FIELDS = " examinId,paperId,questionIds,account,options ";
    String SELECT_FIELDS = " wrongId,examinId,paperId,questionIds,account,options ";
    String TABLE_NAME = " wrong ";

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values(#{examinId},#{paperId},#{questionIds},#{account},#{options})"})
    Integer insertWrong(Wrong wrong);

    @Select({"select"+SELECT_FIELDS+"from"+TABLE_NAME+"where examinId = #{examinId} and account = #{account}"})
    @Results({
         @Result(column = "wrongId",property = "wrongId"),
         @Result(column = "examinId",property = "examinId"),
         @Result(column = "paperId",property = "paperId"),
         @Result(column = "questionIds",property = "questionIds"),
         @Result(column = "account",property = "account"),
         @Result(column = "options",property = "options")
    })
    Wrong selectWrongByExaminIdAndAccount(@Param("examinId")Integer examinId , @Param("account") String account);

}
