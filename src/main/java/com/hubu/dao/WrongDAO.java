package com.hubu.dao;

import com.hubu.pojo.Wrong;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WrongDAO {

    String INSERT_FIELDS = " examinId,paperId,questionIds,account,options ";
    String TABLE_NAME = " wrong ";

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values(#{examinId},#{paperId},#{questionIds},#{account},#{options})"})
    Integer insertWrong(Wrong wrong);
}
