package com.example.demo.dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    //插入用户信息
    @Insert("INSERT INTO user VALUES (#{UID}, #{name}, #{age}, #{gender}, #{photo}, #{intro})")
    int insertUser(User user);

    @Select("SELECT * FROM user WHERE UID = #{uid}")
    User selectUserByUid(String uid);

    @Select("SELECT UID FROM user ORDER BY UID DESC LIMIT 1")
    String getLastId();
}
