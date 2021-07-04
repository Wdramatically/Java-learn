import com.imooc.oa.utils.MybatisUtils;
import org.junit.Test;

/**
 * FileName: ExecuteTest
 * Author:   86155
 * Date:     2021/5/1 22:07
 * Description:
 */

public class ExecuteTest {

    @Test
    public void testExecuteQuery() {
        String o = (String) MybatisUtils.executeQuery(sqlSession -> sqlSession.selectOne("test.select"));
        System.out.println(o);
    }

    @Test
    public void testString(){
        String filename = null;
        int i = filename.lastIndexOf(".");
        System.out.println(i);
    }
}
