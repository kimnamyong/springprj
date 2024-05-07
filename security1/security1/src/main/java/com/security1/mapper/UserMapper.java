package com.security1.mapper;

import com.security1.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

 @Select("SELECT * FROM user WHERE loginName = #{loginName}")
 User findByLoginName(String loginName);
}
