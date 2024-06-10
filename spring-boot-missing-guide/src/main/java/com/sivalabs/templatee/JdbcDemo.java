package com.sivalabs.templatee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcDemo {

    void createCustomer(Customer customer) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into customers(name) values(?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customer.name);
            pstmt.execute();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
        } finally {
            if(conn != null) {
                conn.close();
            }
        }
    }

    void updateCustomer(Customer customer) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            String sql = "update customers set name = ? where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customer.name);
            pstmt.setLong(2, customer.id);
            pstmt.execute();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
        } finally {
            if(conn != null) {
                conn.close();
            }
        }
    }

    private Connection getConnection() {
        try {
            String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
            String jdbcUsername = "postgres";
            String jdbcPassword = "postgres";
            return DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
