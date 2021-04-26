import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.imooc.mybatis.annotation.dao.SongDao;
import com.imooc.mybatis.entity.Classes;
import com.imooc.mybatis.entity.NewStudent;
import com.imooc.mybatis.entity.Song;
import com.imooc.mybatis.entity.Student;
import com.imooc.mybatis.util.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MyBatisTestor {

    @Test
    public void testSqlSessionFactory() throws IOException {
        //利用Reader加载classpath下的配置文件
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        //初始化SqlSessionFactory对象，同时解析配置文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = null;
        try {
            //创建sqlSession，SQLSession是JDBC的扩展类，用于与数据库交互
            sqlSession = sqlSessionFactory.openSession();
            List<Student> students = sqlSession.selectList("student.selectAll");
            students.stream().forEach(System.out::println);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (sqlSession != null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void testInsert(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSqlSession();
            Student student = new Student();
            student.setName("zyn");
            student.setAge(25);
            student.setSex("女");
            student.setGrade("初一");
            student.setMajor("电子");
            student.setRegNo(20200202);
            int insert = sqlSession.insert("insert",student);
            sqlSession.commit();
            Integer id = student.getId();
            System.out.println(id);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testManyToOne(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSqlSession();
            List<NewStudent> students = sqlSession.selectList("student.selectManyToOne", "Class001");
            sqlSession.commit();
            for (NewStudent student : students){
                System.out.println(student);
            }
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
    }

    @Test
    public void testOneToMany(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSqlSession();
            List<Classes> classes = sqlSession.selectList("classes.selectOneToMany");
            System.out.println(classes);
            sqlSession.commit();

            sqlSession = MyBatisUtils.openSqlSession();
            classes = sqlSession.selectList("classes.selectOneToMany");
            System.out.println(classes);
            for (Classes classes1 : classes){
                System.out.println(classes1);
            }
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
    }

    @Test
    public void testPage(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSqlSession();
            PageHelper.startPage(2,2);
            Page page = (Page) sqlSession.selectList("student.selectPage");
            System.out.println("页数：" + page.getPageNum());
            System.out.println("起始行：" + page.getStartRow());
            System.out.println("页面大小：" + page.getPageSize());
            List<NewStudent> result = page.getResult();
            for (NewStudent student: result){
                System.out.println(student);
            }
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
    }

    @Test
    public void testBatchInsert(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSqlSession();
            NewStudent student = null;
            List<NewStudent> students = new ArrayList<>();
            long start = new Date().getTime();
            for (int i = 0; i < 10000; i++) {
                student = new NewStudent();
                student.setStuNo("2015" + i);
                student.setStuName("hxd" + i);
                student.setSex("男");
                student.setClassNo("Class002");
                students.add(student);
            }
            sqlSession.insert("student.batchInsert",students);
            sqlSession.commit();
            long end = new Date().getTime();
            System.out.println("使用了" + (end-start) + "ms");
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
    }

    @Test
    public void testBatchDelete(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSqlSession();
            List<Integer> students = new ArrayList<>(Arrays.asList(10012, 10015, 10017));
            long start = new Date().getTime();
            sqlSession.delete("student.batchDelete", students);
            sqlSession.commit();
            long end = new Date().getTime();
            System.out.println("使用了" + (end-start) + "ms");
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
    }

    @Test
    public void testAnnotation(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSqlSession();
            SongDao songDAO = sqlSession.getMapper(SongDao.class);
            List<Song> songs = songDAO.findAll();
            for (Song song : songs) {
                System.out.println(song);
            }
            System.out.println("===========================================");
            List<Song> songsByType = songDAO.findByType("流行");
            for (Song song : songsByType) {
                System.out.println(song);
            }

            Song song = new Song();
            song.setName("给我一首歌的时间");
            song.setSinger("周杰伦");
            song.setCategory("流行");
            song.setWriter("周杰伦");
            song.setLanguage("国语");
            song.setIssudate(java.sql.Date.valueOf("2008-10-14"));
            int i = songDAO.insertOne(song);
            if (i > 0){
                System.out.println("添加成功");
            }
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
    }

}
