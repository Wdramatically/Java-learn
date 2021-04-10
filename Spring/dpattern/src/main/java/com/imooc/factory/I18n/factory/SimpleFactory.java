package com.imooc.factory.I18n.factory;

import com.imooc.factory.I18n.area.Chinese;
import com.imooc.factory.I18n.area.I18n;
import com.imooc.factory.I18n.area.Italian;

public class SimpleFactory {

    public static I18n getInternationalization(String area){
        if (area == "China"){
            return new Chinese();
        }else if(area == "Italy"){
            return new Italian();
        }
        return null;
    }
}
