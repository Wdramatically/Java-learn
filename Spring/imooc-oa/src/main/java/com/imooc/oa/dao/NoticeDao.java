package com.imooc.oa.dao;

import com.imooc.oa.entity.Notice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NoticeDao {

    @Insert("INSERT INTO sys_notice( receiver_id, content, create_time) VALUES (#{receiverId}, #{content}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "noticeId")
    void insert(Notice notice);

    @Select("select * from sys_notice where receiver_id = #{employeeId}")
    List<Notice> selectNoticeByEmployeeId(Long employeeId);

}
