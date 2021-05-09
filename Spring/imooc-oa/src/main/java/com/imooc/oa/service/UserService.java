package com.imooc.oa.service;

import com.imooc.oa.dao.LoginDao;
import com.imooc.oa.entity.User;
import com.imooc.oa.service.exception.BusinessException;

public class UserService {
    private LoginDao loginDao = new LoginDao();

    /**
     * 通过用户名密码检查是否能成功登录
     * @param username
     * @param password
     * @return
     */
    public User checkLoginUser(String username, String password){
        User user = loginDao.selectUserByUsername(username);
        if (user == null){
            throw new BusinessException("L001","没有该用户名");
        }
        if (!user.getPassword().equals(password)){
            throw new BusinessException("L002","密码错误");
        }
        return user;
    }
}
