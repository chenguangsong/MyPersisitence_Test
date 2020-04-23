package com.study.io;

import java.io.InputStream;

/**
 * @ClassName Resource
 * @Description 读取配置文件
 * @Author chenguang
 * @Date 2020-04-23 22:55
 * @Version 1.0
 **/
public class Resource {

    /**
    * @author chenguang
    * @Description //读取传入的配置文件
    * @CreateDate 2020-04-23 22:57
    * @Param [path]
    * @return java.io.InputStream
    **/
    public static InputStream getResourceAsInputStream(String path){
        return Resource.class.getClassLoader().getResourceAsStream(path);
    }
}
