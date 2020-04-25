//package com.study.dao;
//
//import com.study.pojo.UserInfo;
//import com.study.sqlsession.SqlSession;
//import com.study.sqlsession.SqlSessionFactoryBuilder;
//import com.study.sqlsession.SqlsessionFactory;
//import org.dom4j.DocumentException;
//
//import java.beans.IntrospectionException;
//import java.beans.PropertyVetoException;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.lang.reflect.InvocationTargetException;
//import java.sql.SQLException;
//import java.util.List;
//
///**
// * @ClassName UserInfoDao
// * @Description TODO
// * @Author songchenguang
// * @Date 2020-04-25 17:43
// * @Version 1.0
// **/
//public class UserInfoDao implements IUserInfoDao {
//    @Override
//    public List<UserInfo> findAll() throws FileNotFoundException, IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException, PropertyVetoException, DocumentException {
//        InputStream inputStream = new FileInputStream(new File("/Users/songchenguang/IdeaProjects/MyPersisitence_Test/src/main/resources/SqlMapperConfig"));
//        SqlsessionFactory sqlsessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlsessionFactory.openSession();
//        List<UserInfo> objects = sqlSession.selectList("com.study.pojo.UserInfo.selectList");
//        for (Object object : objects) {
//            System.out.println(object.toString());
//        }
//        return objects;
//    }
//
//    @Override
//    public UserInfo findByCondition(UserInfo userInfo) throws FileNotFoundException, PropertyVetoException, DocumentException, IllegalAccessException, ClassNotFoundException, IntrospectionException, InstantiationException, SQLException, InvocationTargetException, NoSuchFieldException {
//        InputStream inputStream = new FileInputStream(new File("/Users/songchenguang/IdeaProjects/MyPersisitence_Test/src/main/resources/SqlMapperConfig"));
//        SqlsessionFactory sqlsessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlsessionFactory.openSession();
//        UserInfo userInfo1 = sqlSession.selectOne("com.study.pojo.UserInfo.selectOne", userInfo);
//        System.out.println(userInfo1.toString());
//        return userInfo1;
//    }
//
//
//
//
//}
