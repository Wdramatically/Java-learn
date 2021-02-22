package com.imooc.Spring.jdbc.annotation.service;

import com.imooc.Spring.jdbc.annotation.dao.HotelDAO;
import com.imooc.Spring.jdbc.annotation.entity.Hotel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class HotelService {
    @Resource
    private HotelDAO hotelDAO;

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Hotel findHotel() {
        return hotelDAO.findHotelById(10001L);
    }

    public void batchImport() {
        try {
            for (int i = 0; i < 10; i++) {
                Hotel hotel = new Hotel();
                hotel.setOrderNo(10010 + i);
                if (i==3){
                    throw new RuntimeException("意料之外的异常");
                }
                hotel.setCity("上海");
                hotel.setPrice(4000f);
                hotel.setHotelName("酒店" + i);
                hotel.setArriveDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-12"));
                hotel.setLeaveDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-15"));
                hotelDAO.insert(hotel);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
