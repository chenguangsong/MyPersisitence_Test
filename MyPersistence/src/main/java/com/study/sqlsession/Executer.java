package com.study.sqlsession;

import com.study.pojo.Configuration;
import com.study.pojo.SqlStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface Executer {

    <E> List<E> query(Configuration configuration, SqlStatement sqlStatement,Object... param) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException, IntrospectionException, InvocationTargetException;

    boolean excute(Configuration configuration, SqlStatement sqlStatement,Object... param) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException;
}
