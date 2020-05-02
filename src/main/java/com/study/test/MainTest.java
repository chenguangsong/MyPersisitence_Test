package com.study.test;

import com.study.dao.IUserInfoDao;
import com.study.io.Resource;
import com.study.pojo.UserInfo;
import com.study.sqlsession.SqlSession;
import com.study.sqlsession.SqlSessionFactoryBuilder;
import com.study.sqlsession.SqlsessionFactory;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
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

    InputStream inputStream;
    SqlsessionFactory sqlsessionFactory;
    SqlSession sqlSessionsion;

    @Before
    public void before() throws DocumentException, PropertyVetoException, FileNotFoundException {
        //读取配置文件
        inputStream = Resource.getResourceAsInputStream("SqlMapperConfig");
        sqlsessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSessionsion = sqlsessionFactory.openSession();
    }

    @Test
    public void addTest(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(3);
        userInfo.setUserName("王五");
        IUserInfoDao mapper = sqlSessionsion.getMapper(IUserInfoDao.class);
        mapper.saveUserInfo(userInfo);
    }

    @Test
    public void updateTest(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("aaaa");
        userInfo.setUserId(3);
        IUserInfoDao mapper = sqlSessionsion.getMapper(IUserInfoDao.class);
        mapper.updateUserInfoById(userInfo);
    }

    @Test
    public void deleteTest(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(3);
        IUserInfoDao mapper = sqlSessionsion.getMapper(IUserInfoDao.class);
        mapper.deleteUserInfoById(userInfo);
    }

    @Test
    public void findAll() throws FileNotFoundException, InstantiationException, IntrospectionException, SQLException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, PropertyVetoException, ClassNotFoundException, DocumentException {
        IUserInfoDao mapper = sqlSessionsion.getMapper(IUserInfoDao.class);
        List<UserInfo> userInfos = mapper.findAll();
        for (UserInfo userInfo : userInfos) {
            System.out.println(userInfo.toString());
        }
    }

    @Test
    public void findByCondition() throws IllegalAccessException, FileNotFoundException, InstantiationException, IntrospectionException, SQLException, PropertyVetoException, InvocationTargetException, NoSuchFieldException, ClassNotFoundException, DocumentException {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1);
        IUserInfoDao mapper = sqlSessionsion.getMapper(IUserInfoDao.class);
        UserInfo userInfo1 = mapper.findByCondition(userInfo);
        System.out.println(userInfo1.toString());

    }

}
