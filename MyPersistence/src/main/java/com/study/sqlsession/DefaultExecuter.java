package com.study.sqlsession;

import com.study.config.BoundSql;
import com.study.pojo.Configuration;
import com.study.pojo.SqlStatement;
import com.study.utils.GenericTokenParser;
import com.study.utils.ParameterMapping;
import com.study.utils.ParameterMappingTokenHandler;

import javax.sql.DataSource;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DefaultExecuter
 * @Description TODO
 * @Author chenguang
 * @Date 2020-04-25 14:02
 * @Version 1.0
 **/
public class DefaultExecuter implements Executer {
    @Override
    public <E> List<E> query(Configuration configuration, SqlStatement sqlStatement, Object... param) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException, IntrospectionException, InvocationTargetException {

        //注册驱动，获取连接
        DataSource dataSource = configuration.getDataSource();
        Connection connection = dataSource.getConnection();
        //获取sql语句
        String sql = sqlStatement.getSql();
        //转换sql语句 将占位符转换为'?'，且将sql参数解析存储
        BoundSql boundSql = getBoundSql(sql);
        //获取预处理对象
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());
        //绑定参数

        String paramType = sqlStatement.getParamType();
        Class<?> paramClassType = getClassType(paramType);

        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for(int i = 0; i < parameterMappingList.size(); i++){
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();
            //反射
            Field declaredField = paramClassType.getDeclaredField(content);
            //暴力访问
            declaredField.setAccessible(true);
            Object o = declaredField.get(param[0]);
            preparedStatement.setObject(++i,o);
        }
        //执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        //封装返回结果集
        String resultType = sqlStatement.getResultType();
        Class<?> resultclassType = getClassType(resultType);

        List<Object> objects = new ArrayList<>();
        while(resultSet.next()){
            Object o = resultclassType.newInstance();
            ResultSetMetaData metaData = resultSet.getMetaData();
            for(int i = 1; i <= metaData.getColumnCount(); i++){
                //字断名称
                String columnName = metaData.getColumnName(i);
                //获取到返回值
                Object value = resultSet.getObject(columnName);
                //使用反射/内省根据数据库表和实体的对应关系完成封装，实体中的属性名与数据库字断名一致
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultclassType);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o,value);

            }
            objects.add(o);
        }
        return (List<E>) objects;
    }

    /**
    * @author chenguang
    * @Description //转换sql，将'#{}'转换为'?',解析出#{}中的参数进行存储
    * @CreateDate 2020-04-25 14:20
    * @Param [sql]
    * @return com.study.config.BoundSql
    **/
    private BoundSql getBoundSql(String sql){
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        // 将'#{}'转换为'?'
        String parseSql = genericTokenParser.parse(sql);
        //#{}中的参数名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql(parseSql, parameterMappings);
        return boundSql;
    }


    public Class<?> getClassType(String path) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if(null != path && !"".equals(path)){
            return (Class<?>) Class.forName(path);
        }else{
            return null;
        }

    }

}
