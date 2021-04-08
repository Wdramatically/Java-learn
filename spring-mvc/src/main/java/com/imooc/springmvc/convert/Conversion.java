package com.imooc.springmvc.convert;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversion implements Converter<String, Date> {
    public Date convert(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(time);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }
}
