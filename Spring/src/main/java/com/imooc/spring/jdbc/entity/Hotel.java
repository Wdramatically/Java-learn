package com.imooc.spring.jdbc.entity;

import java.util.Date;

public class Hotel {
    private Integer orderNo;
    private String city;
    private float price;
    private String hotelName;
    private Date arriveDate;
    private Date leaveDate;

    public Hotel() {
    }

    /*public Hotel(Integer orderNo, String city, float price, String hotelName, Date arriveDate, Date leaveDate) {
        this.orderNo = orderNo;
        this.city = city;
        this.price = price;
        this.hotelName = hotelName;
        this.arriveDate = arriveDate;
        this.leaveDate = leaveDate;
    }*/

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Date getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    @Override
    public String toString() {
        return "hotel{" +
                "orderNo=" + orderNo +
                ", city='" + city + '\'' +
                ", price=" + price +
                ", hotelName='" + hotelName + '\'' +
                ", arriveDate=" + arriveDate +
                ", leaveDate=" + leaveDate +
                '}';
    }
}
