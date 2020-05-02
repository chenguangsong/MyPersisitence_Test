#创建数据库
CREATE DATABASE LAGOU_STUDY;

USE LAGOU_STUDY;

#创建用户表
CREATE TABLE IF NOT EXISTS USERINFO(
userId INT NOT NULL COMMENT '用户id',
userName VARCHAR(100) NOT NULL COMMENT '用户名称'
)ENGINE=INNODB DEFAULT CHARSET=UTF8;

INSERT INTO USERINFO VALUES(1,'zs');
INSERT INTO USERINFO VALUES(2,'李四');

SELECT * FROM USERINFO;