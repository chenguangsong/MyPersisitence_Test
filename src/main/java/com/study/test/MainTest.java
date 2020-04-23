package com.study.test;

import com.study.io.Resource;
import org.junit.Test;

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
    public void Test(){
        InputStream inputStream = Resource.getResourceAsInputStream("SqlMapperConfig.xml");
    }
}
