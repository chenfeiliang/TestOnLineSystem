package com.hubu.dao;

import com.hubu.pojo.MyClass;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassDAO {
    String TABLE_NAME = " classinfo ";
    String INSERT_FIELDS = " className ";
    String SELECT_FIELDS = " classId,className ";

    @Insert("insert into" + TABLE_NAME + "(" + INSERT_FIELDS + ") values(#{className})")
    int insertClass(MyClass myClass);

    @Delete("delete from" + TABLE_NAME + "where classId=#{classId}")
    int deleteClass(int classId);

    @Update("update" + TABLE_NAME + "set className=#{className} where classId = #{classId}")
    int updateClass(MyClass myClass);

    @Select({"select" + SELECT_FIELDS + "from" + TABLE_NAME," order by classId desc limit #{currentPage},#{pageCount}"})
    @Results({
            @Result(id = true,property = "classId",column = "classId"),
            @Result(property = "className",column = "className")
    })
    List<MyClass> selectPageClass(@Param("currentPage") int currentPage,@Param("pageCount") Integer pageCount);

    @Select({"select" + SELECT_FIELDS + "from" + TABLE_NAME + "where className like '%${keyWord}%'"," order by classId desc limit #{currentPage},#{pageCount}"})
    @Results({
            @Result(id = true,property = "classId",column = "classId"),
            @Result(property = "className",column = "className")
    })
    List<MyClass> selectPageClassByKeyWord(@Param("currentPage") int currentPage,@Param("pageCount") Integer pageCount,@Param("keyWord") String keyWord);

    @Select({"select ",SELECT_FIELDS,"from",TABLE_NAME,"where ClassId = #{classId}"})
    @Results({
            @Result(id = true,property = "classId",column = "classId"),
            @Result(property = "className",column = "className")
    })
    MyClass selectClassById(Integer classId);

    @Delete({"delete from",TABLE_NAME,"where classId in (${classIds})"})
    Integer batchDeleteClassById(@Param("classIds") String classIds);
}