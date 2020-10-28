package com.linus.lab.mybatis.jdbc;

import java.sql.*;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/21
 */
public class JDBCMain {

    public static void main(String[] args) throws ClassNotFoundException {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://192.168.0.101:3306/test", "root", "root");

            String sql = "select * from user where id = ?";
            // 获取sql执行者
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 1);

            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();

            resultSet.next();

            System.out.println(resultSet.getLong("id"));
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("phone"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    pstmt.close();
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

    }
}
