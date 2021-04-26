package com.imooc.mybatis.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

public class C3P0DatasourceFactory extends UnpooledDataSourceFactory {
    public C3P0DatasourceFactory(){
        //combo代表复杂型的
        this.dataSource = new ComboPooledDataSource();
    }
}
