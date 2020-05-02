package com.study.sqlsession;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface SqlSession {

    /**
    * @author chenguang
    * @Description //TODO
    * @CreateDate 2020-04-25 13:54
    * @Param [statementId, param]
    * @return java.util.List<E>
    **/
    <E> List<E> selectList (String statementId, Object... param) throws IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException;

    /**
    * @author chenguang
    * @Description //根据查询条件查询单个
    * @CreateDate 2020-04-25 13:55
    * @Param [statementId, param]
    * @return T
    **/
    <T> T selectOne (String statementId, Object... param) throws IllegalAccessException, ClassNotFoundException, IntrospectionException, InstantiationException, SQLException, InvocationTargetException, NoSuchFieldException;

    /**
    * @author chenguang
    * @Description //修改操作
    * @CreateDate 2020-05-02 19:12
    * @Param [statementId, param]
    * @return boolean
    **/
    boolean execute(String statementId, Object... param) throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    /**
    * @author chenguang
    * @Description //使用JDK动态代理生成dao接口实现类
    * @CreateDate 2020-04-25 17:53
    * @Param [mapperClass]
    * @return T
    **/
    <T> T getMapper(Class<?> mapperClass);
}
