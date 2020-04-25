package com.study.sqlsession;

import com.study.pojo.Configuration;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName DefaultSqlSession
 * @Description TODO
 * @Author chenguang
 * @Date 2020-04-25 13:46
 * @Version 1.0
 **/
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... param) throws IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException {

        DefaultExecuter defaultExecuter = new DefaultExecuter();

        List<Object> objectList = defaultExecuter.query(configuration, configuration.getMap().get(statementId), param);

        return (List<E>) objectList;
    }

    @Override
    public <T> T selectOne(String statementId, Object... param) throws IllegalAccessException, ClassNotFoundException, IntrospectionException, InstantiationException, SQLException, InvocationTargetException, NoSuchFieldException {
        List<Object> objects = selectList(statementId, param);
        if(objects != null && objects.size() >= 1){
            return (T) objects.get(0);
        }else{
            System.out.println("objects.size:"+objects.size());
            throw new RuntimeException("返回结果多个……");
        }
    }
}
