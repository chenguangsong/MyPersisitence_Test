package com.study.pojo;

/**
 * @ClassName UserInfo
 * @Description 用户信息
 * @Author chenguang
 * @Date 2020-04-23 22:39
 * @Version 1.0
 **/
public class UserInfo {

    private String userName;

    private String userId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
