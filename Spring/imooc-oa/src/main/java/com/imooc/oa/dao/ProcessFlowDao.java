package com.imooc.oa.dao;

import com.imooc.oa.entity.ProcessFlow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ProcessFlowDao {

    @Insert("INSERT INTO adm_process_flow(form_id, operator_id, action, result, reason, create_time , audit_time , order_no , state,is_last) " +
            "VALUES (#{formId}, #{operatorId}, #{action}, #{result}, #{reason}, #{createTime} , #{auditTime} , #{orderNo} , #{state} , #{isLast});")
    @Options(useGeneratedKeys = true, keyProperty = "processId")
    void insert(ProcessFlow processFlow);

    @Select("select * from adm_process_flow where form_id = #{value}")
    List<ProcessFlow> selectProcessFlowByFormId(Long formId);

    @Update("update adm_process_flow set result = #{result}, reason = #{reason}, audit_time = #{auditTime}, state = #{state} where process_id = #{processId}")
    void update(ProcessFlow processFlow);
}
