package com.imooc.jdbc.news;

import com.imooc.jdbc.news.dao.DbUtils;
import com.imooc.jdbc.news.entity.News;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class newsApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            printMenu();
            try {
                int option = input.nextInt();
                if (option > 5 || option < 1) {
                    System.out.println("输入错误,请输入1-5之间的数字进行操作");
                    continue;
                }
                switch (option) {
                    case 1: {
                        System.out.println("请输入新闻标题：");
                        String title = input.next();
                        System.out.println("请输入新闻内容：");
                        String content = input.next();
                        System.out.println("请输入新闻日期，按年-月-日的请示输入");
                        String createTime = input.next();
                        DbUtils.insert(title, content, createTime);
                        DbUtils.query();
                        break;
                    }
                    case 2: {
                        System.out.println("新闻列表如下");
                        DbUtils.query();
                        break;
                    }
                    case 3: {
                        DbUtils.query();
                        System.out.println("请输入要修改的新闻id");
                        try {
                            int id = input.nextInt();
                            News news = DbUtils.queryById(id);
                            if (news != null) {
                                    System.out.println(news);
                            } else {
                                System.out.println("您输入的id有误");
                                break;
                            }
                            System.out.println("请输入要修改的新闻标题");
                            String title = input.next();
                            System.out.println("请输入要修改的新闻内容");
                            String content = input.next();
                            DbUtils.update(id, title, content);
                        } catch (InputMismatchException e) {
                            System.out.println("您输入的类型不正确，请输入id");
                            input.next();
                            break;
                        }
                        break;
                    }
                    case 4: {
                        DbUtils.query();
                        System.out.println("请输入要删除的新闻id");
                        try {
                            int id = input.nextInt();
                            News news = DbUtils.queryById(id);
                            if (news == null) {
                                System.out.println("您输入的id有误");
                                break;
                            }
                            DbUtils.delete(id);
                        } catch (InputMismatchException e) {
                            System.out.println("您输入的类型不正确，请输入id");
                            input.next();
                            break;
                        }
                        break;
                    }
                    case 5: {
                        System.out.println("退出系统！");
                        System.exit(0);
                    }
                    default: {
                        System.out.println("您的输入有误");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("输入有误，请输入1-5的数字");
                input.next();
                continue;
            }
        }
    }

    public static void printMenu() {
        System.out.println("欢迎来到新闻管理系统");
        System.out.println("===================");
        System.out.println("     1.添加新闻");
        System.out.println("     2.查看新闻");
        System.out.println("     3.编辑新闻");
        System.out.println("     4.删除新闻");
        System.out.println("     5.退出新闻");
        System.out.println("请输入1-5之间的数字进行操作");
    }
}
