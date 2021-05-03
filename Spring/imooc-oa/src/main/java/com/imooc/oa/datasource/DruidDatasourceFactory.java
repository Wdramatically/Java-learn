/**
 * FileName: DruidDatasourceFactory
 * Author:   86155
 * Date:     2021/5/1 21:50
 * Description:
 */

package com.imooc.oa.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DruidDatasourceFactory extends UnpooledDataSourceFactory {
    public DruidDatasourceFactory(){
        this.dataSource = new DruidDataSource();
    }

    @Override
    public DataSource getDataSource() {
        try {
            ((DruidDataSource)this.dataSource).init();
            return this.dataSource;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
