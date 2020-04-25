package com.study.sqlsession;

import com.study.pojo.Configuration;

/**
 * @ClassName DefaultSqlSessionFactory
 * @Description TODO
 * @Author songchenguang
 * @Date 2020-04-25 13:43
 * @Version 1.0
 **/
public class DefaultSqlSessionFactory implements SqlsessionFactory{

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
