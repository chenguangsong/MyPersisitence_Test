package com.study.pojo;

/**
 * @ClassName SqlStatement
 * @Description sql映射类
 * @Author chenguang
 * @Date 2020-04-23 23:09
 * @Version 1.0
 **/
public class SqlStatement {

    private String id;
    private String paramType;
    private String resultType;
    private String sql;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "SqlStatement{" +
                "id='" + id + '\'' +
                ", paramType='" + paramType + '\'' +
                ", resultType='" + resultType + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }
}
