package com.irwin13.winwork.mybatis.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author irwin Timestamp : 17/04/2014 16:17
 */
public class YesNoBooleanTypeHandler implements TypeHandler<Boolean> {

    private static final String YES = "Y";
    private static final String NO = "N";

    @Override
    public void setParameter(PreparedStatement preparedStatement, int columnPosition, Boolean value, JdbcType jdbcType)
            throws SQLException {
        if (value == null) {
            preparedStatement.setString(columnPosition, NO);
        } else {
            preparedStatement.setString(columnPosition, value ? YES : NO);
        }
    }

    @Override
    public Boolean getResult(ResultSet resultSet, String columnName) throws SQLException {
        return valueOf(resultSet.getString(columnName));
    }

    @Override
    public Boolean getResult(ResultSet resultSet, int columnPosition) throws SQLException {
        return valueOf(resultSet.getString(columnPosition));
    }

    @Override
    public Boolean getResult(CallableStatement callableStatement, int columnPosition) throws SQLException {
        return valueOf(callableStatement.getString(columnPosition));
    }

    private Boolean valueOf(String value) throws SQLException {
        if (YES.equalsIgnoreCase(value)) {
            return Boolean.TRUE;
        } else {
            // other than 'Y' or 'y' will return FALSE
            return Boolean.FALSE;
        }
    }
}
