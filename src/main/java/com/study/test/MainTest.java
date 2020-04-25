package com.study.test;

import com.study.io.Resource;
import com.study.pojo.User;
import com.study.sqlsession.SqlSession;
import com.study.sqlsession.SqlSessionFactoryBuilder;
import com.study.sqlsession.SqlsessionFactory;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @ClassName MainTest
 * @Description 自定义持久层框架测试类
 * @Author chenguang
 * @Date 2020-04-23 22:44
 * @Version 1.0
 **/
public class MainTest {

    @Test
    public void Test() throws PropertyVetoException, DocumentException {
        InputStream inputStream = Resource.getResourceAsInputStream("SqlMapperConfig.xml");

        SqlsessionFactory sqlsessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlsessionFactory.openSession();
        User user = new User();
        user.setId("1");
        user.setName("zs");
        Object o = sqlSession.selectOne("com.study.User.selectOne", user);
        System.out.println(o.toString());
    }
}
