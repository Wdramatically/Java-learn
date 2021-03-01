package com.imooc.springmvc.entity;

public class Delivery {
    private String name;
    private String address;
    private String telPhone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telPhone='" + telPhone + '\'' +
                '}';
    }
}
