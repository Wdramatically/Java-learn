package com.imooc.oa.dao;

import com.imooc.oa.entity.Department;
import org.apache.ibatis.annotations.Select;

public interface DepartmentDao {

    @Select("select * from adm_department where department_id = #{departmentId}")
    Department selectDepartmentById(Long departmentId);
}
