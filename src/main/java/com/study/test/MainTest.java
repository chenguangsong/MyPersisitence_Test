package com.study.test;

import com.study.pojo.UserInfo;
import com.study.sqlsession.SqlSession;
import com.study.sqlsession.SqlSessionFactoryBuilder;
import com.study.sqlsession.SqlsessionFactory;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName MainTest
 * @Description 自定义持久层框架测试类
 * @Author chenguang
 * @Date 2020-04-23 22:44
 * @Version 1.0
 **/
public class MainTest {


    @Test
    public void Test() throws PropertyVetoException, DocumentException, IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException, FileNotFoundException {
//        InputStream resourceAsSteam = Resource.getResourceAsInputStream("SqlMapperConfig.xml");
        InputStream inputStream = new FileInputStream(new File("/Users/songchenguang/IdeaProjects/MyPersisitence_Test/src/main/resources/SqlMapperConfig"));
        SqlsessionFactory sqlsessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlsessionFactory.openSession();

        UserInfo user = new UserInfo();
        user.setUserId(1);
        user.setUserName("李四");

        Object o = sqlSession.selectOne("com.study.pojo.UserInfo.selectOne", user);

        System.out.println(o.toString());

        System.out.println("==================");
        List<Object> objects = sqlSession.selectList("com.study.pojo.UserInfo.selectList", user);
        for (Object object : objects) {
            System.out.println(object.toString());
        }
    }
}
