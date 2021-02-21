package com.imooc.spring.jdbc.service.impl;

import com.imooc.spring.jdbc.dao.EmployeeDao;
import com.imooc.spring.jdbc.entity.Employee;
import com.imooc.spring.jdbc.service.EmployeeService;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Date;


public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;
//    private DataSourceTransactionManager transactionManager;
    public void batchImport() {
        /*DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
        try {*/
            for (int i = 0; i < 10; i++) {
                if (i==3){
                    throw new RuntimeException("意料之外~~~");
                }
                Employee employee = new Employee();
                employee.setEno(8000 + i);
                employee.seteName("员工" + i);
                employee.setSalary(4000);
                employee.setdName("研发部");
                employee.setHireDate(new Date());
                employeeDao.insert(employee);
            }
//            transactionManager.commit(status);
            System.out.println("插入成功！");
        /*}catch (Exception e){
            transactionManager.rollback(status);
            e.printStackTrace();
        }*/
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    /*public DataSourceTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(DataSourceTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }*/
}
