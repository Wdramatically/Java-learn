package com.imooc.oa.service;

import com.imooc.oa.dao.NoticeDao;
import com.imooc.oa.entity.Notice;
import com.imooc.oa.utils.MybatisUtils;

import java.util.List;

public class NoticeService {
    public List<Notice> getNotice(Long employeeId){
        return (List<Notice>) MybatisUtils.executeQuery(sqlSession -> {
            NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
            return noticeDao.selectNoticeByEmployeeId(employeeId);
        });
    }
}
