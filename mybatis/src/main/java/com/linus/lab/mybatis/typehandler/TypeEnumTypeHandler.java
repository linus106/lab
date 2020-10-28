package com.linus.lab.mybatis.typehandler;

import com.linus.lab.mybatis.pojo.BlogTypeEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/28
 */
public class TypeEnumTypeHandler extends BaseTypeHandler<BlogTypeEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BlogTypeEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public BlogTypeEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return BlogTypeEnum.getEnumByCode(rs.getInt(columnName));
    }

    @Override
    public BlogTypeEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return BlogTypeEnum.getEnumByCode(rs.getInt(columnIndex));
    }

    @Override
    public BlogTypeEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return BlogTypeEnum.getEnumByCode(cs.getInt(columnIndex));
    }
}
