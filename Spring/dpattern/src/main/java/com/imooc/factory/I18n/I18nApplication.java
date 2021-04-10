package com.imooc.factory.I18n;

import com.imooc.factory.I18n.area.I18n;
import com.imooc.factory.I18n.factory.SimpleFactory;

public class I18nApplication {
    public static void main(String[] args) {
        I18n china = SimpleFactory.getInternationalization("Italy");
        System.out.println(china.getTitle());
    }
}
