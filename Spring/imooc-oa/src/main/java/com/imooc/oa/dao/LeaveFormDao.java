package com.imooc.oa.dao;

import com.imooc.oa.entity.LeaveForm;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface LeaveFormDao {

    @Insert("INSERT INTO adm_leave_form( employee_id, form_type, start_time, end_time, reason, create_time, state)" +
            " VALUES ( #{employeeId}, #{formType}, #{startTime}, #{endTime}, #{reason}, #{createTime}, #{state})")
    @Options(useGeneratedKeys = true, keyProperty = "formId", keyColumn = "from_id")
    void insert(LeaveForm leaveForm);

    @Select("select f.*,e.name,d.department_name from adm_leave_form f, adm_process_flow pf, adm_employee e, adm_department d\n" +
            "where f.form_id = pf.form_id \n" +
            "and e.employee_id = f.employee_id\n" +
            "and e.department_id = d.department_id\n" +
            "and pf.operator_id = #{userId} \n" +
            "and pf.state = #{state};")
    List<Map> selectLeaveFormList(@Param("userId") Long userId, @Param("state") String state);

    @Select("select * from adm_leave_form where form_id = #{value}")
    LeaveForm selectById(Long formId);

    @Update("update adm_leave_form set state = #{state}, end_time = #{endTime}, reason = #{reason} where form_id = #{formId}")
    void update(LeaveForm leaveForm);

}
