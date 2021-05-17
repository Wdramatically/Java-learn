package com.imooc.oa.dao;

import com.imooc.oa.entity.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmployeeDao {

    @Select("select * from adm_employee where employee_id = #{employeeId}")
    Employee selectEmployeeById(Long employeeId);
}
