package com.hubu.dao;

import com.hubu.pojo.Card;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CardDAO {
    String INSERT_FIELDS = " examinId,paperId,account,options ";
    String TABLE_NAME = " card ";
    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,")","values(#{examinId},#{paperId},#{account},#{options})"})
    Integer insertCard(Card card);

    @Select({"select count(*) from"+TABLE_NAME+"where examinId=#{examinId} and account = #{account}"})
    Integer isExit(@Param("examinId") Integer examinId,@Param("account") String account);

    @Update({"update"+TABLE_NAME+"set"+" options=#{options} where examinId=#{examinId} and account = #{account}"})
    Integer updateCard(@Param("examinId") Integer examinId,@Param("account") String account,@Param("options") String options);
}
