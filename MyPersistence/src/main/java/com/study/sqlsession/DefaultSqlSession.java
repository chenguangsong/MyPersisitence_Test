package com.study.sqlsession;

import com.study.pojo.Configuration;

import java.beans.IntrospectionException;
import java.lang.reflect.*;
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

    @Override
    public boolean execute(String statementId, Object... param) throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        DefaultExecuter defaultExecuter = new DefaultExecuter();
        return defaultExecuter.excute(configuration,configuration.getMap().get(statementId),param);
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        Object o = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            /**
            * @author chenguang
            * @Description
            * @CreateDate 2020-04-25 17:59
            * @Param [proxy:当前代理对象的引用, method:当前被调用的方法, args:被调用方法参数]
            * @return java.lang.Object
            **/
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //
                String methodName = method.getName();
                String namespace = method.getDeclaringClass().getName();
                String statementId = namespace + "." + methodName;
                //获取被调用方法返回值类型
                Type genericReturnType = method.getGenericReturnType();
                //判断被调用方法返回类型是否进行类范型参数化
                if(genericReturnType instanceof ParameterizedType){
                    List<Object> objects = selectList(statementId, args);
                    return objects;
                }else if(genericReturnType.getTypeName().equals("void")){
                    return execute(statementId,args);
                }
                return selectOne(statementId,args);
            }
        });

        return (T) o;
    }
}
