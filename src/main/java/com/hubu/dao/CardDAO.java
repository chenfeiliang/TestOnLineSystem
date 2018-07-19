package com.hubu.dao;

import com.hubu.pojo.Card;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardDAO {
    String INSERT_FIELDS = " examinId,paperId,account,options ";
    String TABLE_NAME = " card ";
    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,")","values(#{examinId},#{paperId},#{account},#{options})"})
    Integer insertCard(Card card);
}
