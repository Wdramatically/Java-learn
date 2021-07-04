package com.imooc.oa.dao;

import com.imooc.oa.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface EmployeeDao {

    @Select("select * from adm_employee where employee_id = #{employeeId}")
    Employee selectEmployeeById(Long employeeId);

    @Select({"<script> ",
            "select * from adm_employee where",
                "<if test='emp.level &lt; 7'>",
                    "level = 7 and department_id = #{emp.departmentId}",
                "</if>",
                "<if test='emp.level == 7'>",
                    "level = 8",
                "</if>",
                "<if test='emp.level == 8'>",
                    "employee_id = #{emp.employeeId}",
                "</if>",
            "</script>"})
    Employee selectLeader(@Param("emp") Employee employee);
}
