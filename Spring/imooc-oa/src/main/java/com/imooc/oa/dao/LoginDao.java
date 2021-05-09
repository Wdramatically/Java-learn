package com.imooc.oa.dao;

import com.imooc.oa.entity.User;
import com.imooc.oa.mapper.UserMapper;
import com.imooc.oa.utils.MybatisUtils;

public class LoginDao {

    public User selectUserByUsername(String username) {
        return (User) MybatisUtils.executeQuery(sqlSession -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            return userMapper.selectUserByUsername(username);
        });
    }
}
