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
}
