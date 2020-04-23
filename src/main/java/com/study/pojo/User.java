package com.study.pojo;

/**
 * @ClassName User
 * @Description 用户信息
 * @Author chenguang
 * @Date 2020-04-23 22:39
 * @Version 1.0
 **/
public class User {

    private String name;

    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
