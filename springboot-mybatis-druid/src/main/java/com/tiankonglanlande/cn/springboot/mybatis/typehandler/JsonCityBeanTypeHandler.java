package com.tiankonglanlande.cn.springboot.mybatis.typehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiankonglanlande.cn.springboot.mybatis.bean.CityBean;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonCityBeanTypeHandler extends BaseTypeHandler<CityBean> {
    public ObjectMapper objectMapper=new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, CityBean t, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i,objectMapper.writeValueAsString(t));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CityBean getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String str=resultSet.getString(s);
        if (resultSet.wasNull()){
            return null;
        }
        return getBean(str);
    }

    @Override
    public CityBean getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public CityBean getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }

    private CityBean getBean(String str){
        if (StringUtils.isEmpty(str))
            return null;
        try {
            return objectMapper.readValue(str,CityBean.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
