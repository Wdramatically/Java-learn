package com.imooc.oa.mapper;

import com.imooc.oa.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from sys_user where username = #{username}")
    User selectUserByUsername(String username);
}
