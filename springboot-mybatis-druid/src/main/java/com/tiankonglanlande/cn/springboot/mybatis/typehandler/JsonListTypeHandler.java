package com.tiankonglanlande.cn.springboot.mybatis.typehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * json集合字符串转集合
 * @param <T>
 */
public class JsonListTypeHandler<T> extends BaseTypeHandler<List<T>> {
    public static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,  List<T> parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, objectMapper.writeValueAsString(parameter));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        if (rs.wasNull())
            return null;

        return getBeanList(str);
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        if (rs.wasNull())
            return null;

        return getBeanList(str);
    }

    @Override
    public List<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        if (cs.wasNull())
            return null;

        return getBeanList(str);
    }

    private List<T> getBeanList(String str) {
        if (StringUtils.isEmpty(str)){
            return null;
        }
        try {
            List<T> beanList =objectMapper.readValue(str,new TypeReference<List<T>>(){});
            return beanList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
