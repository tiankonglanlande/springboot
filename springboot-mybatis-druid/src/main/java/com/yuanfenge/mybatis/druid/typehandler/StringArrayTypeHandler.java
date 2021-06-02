package com.yuanfenge.mybatis.druid.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 字符串转int数组
 */
public class StringArrayTypeHandler extends BaseTypeHandler<String[]> {
    private static final String delimiter = ",";

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String[] strings, JdbcType jdbcType) throws SQLException {
        List<String> list = new ArrayList<>();
        for (String item : strings) {
            list.add(String.valueOf(item));
        }
        preparedStatement.setString(i, String.join(delimiter, list));
    }

    @Override
    public String[] getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String str = resultSet.getString(s);
        if (resultSet.wasNull()) {
            return null;
        }
        return str.split(delimiter);
    }


    @Override
    public String[] getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String str = resultSet.getString(i);
        if (resultSet.wasNull()) {
            return null;
        }
        return str.split(delimiter);
    }

    @Override
    public String[] getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String str = callableStatement.getString(i);
        if (callableStatement.wasNull()) {
            return null;
        }
        return str.split(delimiter);
    }
}
