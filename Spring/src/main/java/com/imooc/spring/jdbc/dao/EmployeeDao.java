package com.imooc.spring.jdbc.dao;

import com.imooc.spring.jdbc.entity.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;


public class EmployeeDao {
    private JdbcTemplate jdbcTemplate;

    public Employee findEmployeeById(Integer eno) {
        String sql = "select * from employee where eno = ?";
        Employee employee = jdbcTemplate.queryForObject(sql, new Object[]{eno}, new BeanPropertyRowMapper<Employee>(Employee.class));
        return employee;
    }

    public List<Employee> findEmployeeByDept(String dName) {
        String sql = "select * from employee where dname = ?";
        List<Employee> employeeList = jdbcTemplate.query(sql, new Object[]{dName}, new BeanPropertyRowMapper<Employee>(Employee.class));
        return employeeList;
    }

    public List<Map<String, Object>> findFieldOfEmployee(String eName){
        String sql = "select ename as name, salary as money from employee where ename = ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, new Object[]{eName});
        return maps;
    }

    public int insert(Employee employee){
        String sql = "insert into employee(eno,ename,salary,dname,hiredate) values(?,?,?,?,?)";
        int count = jdbcTemplate.update(sql, new Object[]{
                employee.getEno(), employee.geteName(), employee.getSalary(), employee.getdName(), employee.getHireDate()
        });
        return count;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String toString() {
        return "UserDao{" +
                "jdbcTemplate=" + jdbcTemplate +
                '}';
    }
}
