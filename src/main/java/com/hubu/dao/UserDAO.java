package com.hubu.dao;

import com.hubu.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    String TABLE_NAME = " user ";
    String INSERT_FIELDS = " account,realName,password,classId,mobile,image ";
    @Insert("insert into" + TABLE_NAME + "(" + INSERT_FIELDS + ") values(#{account},#{realName},#{password},#{classId},#{mobile},#{image})")
    int addUser(User user);
}
