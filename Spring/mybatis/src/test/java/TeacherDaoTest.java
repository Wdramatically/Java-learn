import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.imooc.mybatis.annotation.dao.TeacherDao;
import com.imooc.mybatis.entity.Teacher;
import com.imooc.mybatis.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TeacherDaoTest {

    @Test
    public void testTeacher() {
        SqlSession sqlSession = null;
        List<Teacher> teachers = new ArrayList<>();
        Teacher teacher = null;
        try {
            sqlSession = MyBatisUtils.openSqlSession();
            TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
            for (int i = 0; i < 500; i++) {
                teacher = new Teacher();
                teacher.setName("hxd" + i);
                teacher.setSex("男");
                teacher.setSubject("数学");
                teacher.setjNo(i);
                teacher.setGrade(i % 12 + "年级");
                teachers.add(teacher);
            }
            teacherDao.batchInsert(teachers);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testPageSelect(){
        SqlSession sqlSession = null;
        PageHelper.startPage(3,10);
        try {
            sqlSession = MyBatisUtils.openSqlSession();
            TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
            Page page = (Page) teacherDao.pageSelect();
            List<Teacher> result = page.getResult();
            for (Teacher teacher : result){
                System.out.println(teacher);
            }
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }
    }
}
