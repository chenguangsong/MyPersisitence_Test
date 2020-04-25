package com.study.sqlsession;

import com.study.config.XMLConfigBuilder;
import com.study.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @ClassName SqlSessionFactoryBuilder
 * @Description 创建SqlsessionFactory对象
 * @Author chenguang
 * @Date 2020-04-23 22:58
 * @Version 1.0
 **/
public class SqlSessionFactoryBuilder {

    public SqlsessionFactory build(InputStream in) throws PropertyVetoException, DocumentException {
        //解析配置文件 封装为Configuration对象
       Configuration configuration = new XMLConfigBuilder().pathConfig(in);
        //创建SqlsessionFactory对象
        SqlsessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return defaultSqlSessionFactory;
    }
}
