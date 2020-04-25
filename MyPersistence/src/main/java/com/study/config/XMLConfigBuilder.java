package com.study.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.study.pojo.Configuration;
import com.study.pojo.SqlStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName XMLConfigBuilder
 * @Description xml解析器
 * @Author chenguang
 * @Date 2020-04-23 23:12
 * @Version 1.0
 **/
public class XMLConfigBuilder {

    private Configuration configuration;

    public XMLConfigBuilder(){
        this.configuration = new Configuration();
    }

    /**
    * @author chenguang
    * @Description //解析SqlMappConfig.xml 封装为Configuration对象
    * @CreateDate 2020-04-23 23:14
    * @Param [in]
    * @return com.study.pojo.Configuration
    **/
    public Configuration pathConfig(InputStream in) throws DocumentException, PropertyVetoException, FileNotFoundException {
        Document document = new SAXReader().read(in);
        //获取根结点
        Element rootElement = document.getRootElement();
        //循环获取数据库连接信息
        List<Element> propertyList = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        for (Element element : propertyList) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.put(name,value);
        }
        //创建datasource
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(properties.getProperty("driverClass"));
        dataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        dataSource.setUser(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        configuration.setDataSource(dataSource);
        //获取mapper
        List<Element> mappers = document.selectNodes("//mapper");
        for (Element mapper : mappers) {
            parseSqlStatement(mapper);
        }
        return configuration;
    }


    /**
    * @author chenguang
    * @Description //解析mapper.xml  封装为SqlStatement对象
    * @CreateDate 2020-04-23 23:36
    * @Param [element]
    * @return void
    **/
    public void parseSqlStatement(Element element) throws DocumentException, FileNotFoundException {
        String source = element.attributeValue("source");
//        InputStream inputStream = Resource.getResourceAsInputStream(source);
        InputStream inputStream = new FileInputStream(new File("/Users/songchenguang/IdeaProjects/MyPersisitence_Test/src/main/resources/"+"UserMapper"));
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();

        String namespace = rootElement.attributeValue("namespace");

        List<Element> mappers = rootElement.selectNodes("//select");
        for (Element mapper : mappers) {
            SqlStatement sqlStatement = new SqlStatement();
            String id = mapper.attributeValue("id");
            String paramType = mapper.attributeValue("paramType");
            String resultType = mapper.attributeValue("resultType");
            String sql = mapper.getTextTrim();

            sqlStatement.setId(id);
            sqlStatement.setParamType(paramType);
            sqlStatement.setResultType(resultType);
            sqlStatement.setSql(sql);

            String key = namespace + "." + id;
            configuration.getMap().put(key,sqlStatement);
        }
    }

}
