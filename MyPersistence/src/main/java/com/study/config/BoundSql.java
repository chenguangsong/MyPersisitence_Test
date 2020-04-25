package com.study.config;

import com.study.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BoundSql
 * @Description TODO
 * @Author chenguang
 * @Date 2020-04-25 14:13
 * @Version 1.0
 **/
public class BoundSql {

    private String sqlText; //解析过后的sql

    private List<ParameterMapping> parameterMappingList = new ArrayList<>();

    public BoundSql(String sql,List<ParameterMapping> parameterMappings){
        this.sqlText = sql;
        this.parameterMappingList = parameterMappings;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
