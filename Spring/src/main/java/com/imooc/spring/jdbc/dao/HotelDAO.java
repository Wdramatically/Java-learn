package com.imooc.spring.jdbc.dao;

import com.imooc.spring.jdbc.entity.Hotel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class HotelDAO {
    private JdbcTemplate jdbcTemplate;

    public Hotel findHotelById(Long orderNo){
        String sql = "select * from hotel where orderno = ?";
        Hotel hotel = jdbcTemplate.queryForObject(sql, new Object[]{orderNo}, new BeanPropertyRowMapper<Hotel>(Hotel.class));
        return hotel;
    }

    public List<Hotel> findHotelByCity(String city){
        String sql = "select * from hotel where city = ?";
        List<Hotel> hotels = jdbcTemplate.query(sql, new Object[]{city}, new BeanPropertyRowMapper<Hotel>(Hotel.class));
        return hotels;
    }

    public int insert(Hotel hotel){
        String sql = "insert into hotel(orderno,city,price,hotelname,arrivedate,leavedate) values(?,?,?,?,?,?)";
        int count = jdbcTemplate.update(sql, new Object[]{
                hotel.getOrderNo(), hotel.getCity(), hotel.getPrice(), hotel.getHotelName(), hotel.getArriveDate(), hotel.getLeaveDate()
        });
        return count;
    }

    public int update(Hotel hotel){
        String sql = "update hotel set city = ?,price = ?,hotelname = ?,arrivedate = ?,leavedate = ? where orderno = ?";
        int count = jdbcTemplate.update(sql, new Object[]{
                hotel.getCity(), hotel.getPrice(), hotel.getHotelName(), hotel.getArriveDate(), hotel.getLeaveDate(),hotel.getOrderNo()
        });
        return count;
    }

    public int delete(Long orderNo) {
        String sql = "delete from hotel where orderno = ?";
        int count = jdbcTemplate.update(sql, new Object[]{orderNo});
        return count;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
