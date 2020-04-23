package com.study.pojo;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @ClassName Configuration
 * @Description 全局配置类
 * @Author chenguang
 * @Date 2020-04-23 23:08
 * @Version 1.0
 **/
public class Configuration {

    private DataSource dataSource;

    private Map<String,SqlStatement> map;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, SqlStatement> getMap() {
        return map;
    }

    public void setMap(Map<String, SqlStatement> map) {
        this.map = map;
    }
}
