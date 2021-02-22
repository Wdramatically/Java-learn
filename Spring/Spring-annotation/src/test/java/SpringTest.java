import com.imooc.Spring.jdbc.annotation.dao.HotelDAO;
import com.imooc.Spring.jdbc.annotation.entity.Hotel;
import com.imooc.Spring.jdbc.annotation.service.HotelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringTest {

    @Resource
    private HotelService hotelService;

    @Test
    public void testBatchImport(){
        hotelService.batchImport();
    }

    @Test
    public void testFindHotel(){
        Hotel hotel = hotelService.findHotel();
        System.out.println(hotel);
    }


}
