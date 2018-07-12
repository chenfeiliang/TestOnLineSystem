package com.hubu.dao;

import com.hubu.pojo.Msg;
import com.hubu.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDAO {
    String TABLE_NAME = " user ";
    String INSERT_FIELDS = " account,realName,password,classId,mobile,image ";
    String SELECT_FIELDS = " account,realName,password,classId,mobile,image ";

    @Insert("insert into" + TABLE_NAME + "(" + INSERT_FIELDS + ") values(#{account},#{realName},#{password},#{classId},#{mobile},#{image})")
    int addUser(User user);

    @Delete("delete from " + TABLE_NAME + "where account=#{account}")
    int deleteUser(String account);

    @Update("update" + TABLE_NAME + "set realName=#{realName},password=#{password},mobile=#{mobile},image=#{image} where account=#{account}")
    int updateUser(User user);

    @Select("select" + SELECT_FIELDS + "from" + TABLE_NAME + "limit #{currentPage},#{pageCount}")
    @Results({
            @Result(id = true,column = "account",property = "account"),
            @Result(column = "realName",property = "realName"),
            @Result(column = "password",property = "password"),
            @Result(column = "classId",property = "classId"),
            @Result(column = "mobile",property = "mobile"),
            @Result(column = "image",property = "image")
    })
    List<User> selectPageUser(@Param("currentPage") int currentPage,@Param("pageCount") int pageCount);

    @Select("select" + SELECT_FIELDS + "from" + TABLE_NAME + "where account like '%${keyword}%' limit #{currentPage},#{pageCount}")
    @Results({
            @Result(id = true,column = "account",property = "account"),
            @Result(column = "realName",property = "realName"),
            @Result(column = "password",property = "password"),
            @Result(column = "classId",property = "classId"),
            @Result(column = "mobile",property = "mobile"),
            @Result(column = "image",property = "image")
    })
    List<User> selectPageUserByKeyWord(@Param("currentPage") int currentPage,@Param("pageCount") int pageCount,@Param("keyword") String keyword);
}
