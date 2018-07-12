package com.hubu.dao;

import com.hubu.pojo.Msg;
import com.hubu.pojo.MyClass;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassDAO {
    String TABLE_NAME = " classinfo ";
    String INSERT_FIELDS = " className ";
    String SELETE_FIELDS = " classId,className ";

    @Insert("insert into" + TABLE_NAME + "(" + INSERT_FIELDS + ") values(#{className})")
    int insertClass(MyClass myClass);

    @Update("update" + TABLE_NAME + "set className=#{className} where classId = #{classId}")
    int updateClass(MyClass myClass);

    @Delete("delete from" + TABLE_NAME + "where classId=#{classId}")
    int deleteClass(int classId);

    @Select("select" + SELETE_FIELDS + "from" + TABLE_NAME)
    @Results({
            @Result(id = true,property = "classId",column = "classId"),
            @Result(property = "className",column = "className")
    })
    List<MyClass> selectPageClass(@Param("currentPage") int currentPage,@Param("pageCount") Integer pageCount);

    @Select("select" + SELETE_FIELDS + "from" + TABLE_NAME + "where className like '%${keyWord}%'")
    @Results({
            @Result(id = true,property = "classId",column = "classId"),
            @Result(property = "className",column = "className")
    })
    List<MyClass> selectPageClassByKeyWord(@Param("currentPage") int currentPage,@Param("pageCount") Integer pageCount,@Param("keyWord") String keyWord);
}
