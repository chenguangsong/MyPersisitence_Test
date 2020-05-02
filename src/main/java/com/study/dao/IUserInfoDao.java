package com.study.dao;

import com.study.pojo.UserInfo;
import org.dom4j.DocumentException;

import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName IUserInfoDao
 * @Description
 * @Author chenguang
 * @Date 2020-04-25 17:41
 * @Version 1.0
 **/
public interface IUserInfoDao {

    //查询所有
    List<UserInfo> findAll() throws FileNotFoundException, IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException, PropertyVetoException, DocumentException;
    //根据条件查询
    UserInfo findByCondition(UserInfo userInfo) throws FileNotFoundException, PropertyVetoException, DocumentException, IllegalAccessException, ClassNotFoundException, IntrospectionException, InstantiationException, SQLException, InvocationTargetException, NoSuchFieldException;
    //新增用户信息
    void saveUserInfo(UserInfo userInfo);
    //根据用户id更新用户信息
    void updateUserInfoById(UserInfo userInfo);
    //根据用户id删除用户
    void deleteUserInfoById(UserInfo userInfo);
}
