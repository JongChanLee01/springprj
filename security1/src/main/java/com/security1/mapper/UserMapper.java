package com.security1.mapper;

import com.security1.dto.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE loginName = #{loginName}")
    User findByLoginName(String loginName);

    @Select("SELECT * FROM user")
    List<User> findAll();

    @Insert("""
    INSERT user (loginName, password, name, email, enabled, userType, departmentId)
    VALUES (#{loginName}, #{password}, #{name}, #{email}, #{enabled}, #{userType}, #{departmentId})
  """)
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(User user);

}
