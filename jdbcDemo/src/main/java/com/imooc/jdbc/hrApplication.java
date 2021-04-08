package com.imooc.jdbc;

import com.imooc.jdbc.common.*;

import java.util.Scanner;

public class hrApplication {
    public static void main(String[] args) {
        System.out.println("1-查询部门员工");
        System.out.println("2-新员工入职");
        System.out.println("3-员工信息变更");
        System.out.println("4-员工离职");
        System.out.println("请输入您的操作：");
        Scanner input = new Scanner(System.in);
        int option = input.nextInt();
        Command command = null;
        switch (option){
            case 1:
                command = new JDBCSearchCommand();
                command.executor();
                break;
            case 2:
                command = new JDBCInsertCommand();
                command.executor();
                break;
            case 3:
                command = new JDBCUpdateCommand();
                command.executor();
                break;
            case 4:
                command = new JDBCDeleteCommand();
                command.executor();
                break;
            default:
                System.out.println("您输入有误~");
        }
    }
}
