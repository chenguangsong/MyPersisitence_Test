package com.study.sqlsession;

import com.study.pojo.Configuration;

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
    public <E> List<E> selectList(String statementId, Object... param) {

        DefaultExecuter defaultExecuter = new DefaultExecuter();

        List<Object> objectList = defaultExecuter.query(configuration, configuration.getMap().get(statementId), param);

        return (List<E>) objectList;
    }

    @Override
    public <T> T selectOne(String statementId, Object... param) {
        List<Object> objects = selectList(statementId, param);
        if(objects.size() == 1){
            return (T) objects.get(0);
        }else{
            throw new RuntimeException("返回结果多个……");
        }
    }
}
