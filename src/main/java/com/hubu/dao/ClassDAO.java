package com.hubu.dao;

import com.hubu.pojo.Msg;
import com.hubu.pojo.MyClass;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;


@Mapper
public interface ClassDAO {
    String TABLE_NAME = " classinfo ";
    String INSERT_FIELDS = " className ";

    @Insert("insert into" + TABLE_NAME + "(" + INSERT_FIELDS + ") values(#{className})")
    int insertClass(MyClass myClass);

    @Update("update" + TABLE_NAME + "set className=#{className} where classId = #{classId}")
    int updateClass(MyClass myClass);

    @Delete("delete from" + TABLE_NAME + "where classId=#{classId}")
    int deleteClass(int classId);
}
