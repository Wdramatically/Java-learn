import com.imooc.spring.jdbc.dao.EmployeeDao;
import com.imooc.spring.jdbc.dao.HotelDAO;
import com.imooc.spring.jdbc.entity.Employee;
import com.imooc.spring.jdbc.entity.Hotel;
import com.imooc.spring.jdbc.service.EmployeeService;
import com.imooc.spring.jdbc.service.impl.EmployeeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JdbcTemplateTestor {
    @Resource
    private HotelDAO hotelDAO;

    @Resource
    private EmployeeDao employeeDao;

    @Resource
    private EmployeeService employeeServiceImpl;


    @Test
    public void testFindHotelById() {
        Hotel hotel1 = hotelDAO.findHotelById(10001L);
        System.out.println(hotel1);
    }

    @Test
    public void testFindHotelByCity() {
        List<Hotel> hotel = hotelDAO.findHotelByCity("上海");
        System.out.println(hotel);
    }

    @Test
    public void testInsert() throws ParseException {
        Hotel hotel = new Hotel();
        hotel.setOrderNo(10005);
        hotel.setCity("北京");
        hotel.setPrice(588);
        hotel.setHotelName("酒店5");
        hotel.setArriveDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-11"));
        hotel.setLeaveDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-13"));
        int count = hotelDAO.insert(hotel);
//        int count = hotelDAO.insert(new Hotel(10005, "北京", 588f, "酒店5", new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-11" ), new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-13")));
        System.out.println("插入了" + count + "条数据！");
    }

    @Test
    public void testUpdate() throws ParseException {
        Hotel hotel = hotelDAO.findHotelById(10003L);
        hotel.setArriveDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-04-30"));
        hotel.setLeaveDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-03"));
        int count = hotelDAO.update(hotel);
//        int count = hotelDAO.insert(new Hotel(10005, "北京", 588f, "酒店5", new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-11" ), new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-13")));
        System.out.println("更新了" + count + "条数据！");
    }

    @Test
    public void testDelete(){
        int count = hotelDAO.delete(10005L);
        System.out.println("删除了" + count + "条数据！");
    }

    @Test
    public void testEInsert(){
        Employee employee = new Employee();
        employee.setEno(111);
        employee.seteName("hxd");
        employee.setSalary(5555);
        employee.setdName("研发部");
        employee.setHireDate(new Date());
        int insert = employeeDao.insert(employee);
        System.out.println(insert);
    }

    @Test
    public void testBatchImport(){
        employeeServiceImpl.batchImport();
    }


}
