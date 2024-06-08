package org.example;

import java.sql.*;

import static org.example.ConnectionManager.getConnection;

public class UserDao {

    // api를 사용하는 입장에서 커넥션을 닫는 것을 생각하지 않아도 된다. JdbcTemplate이 대신 역할해주기 때문
    public void create(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO USERS VALUES (?,?,?,?)";
        jdbcTemplate.executeUpdate(user, sql, pstat -> {
            pstat.setString(1, user.getUserId());
            pstat.setString(2, user.getPassword());
            pstat.setString(3, user.getName());
            pstat.setString(4, user.getEmail());
        });
    }

    public User findUserId(String userId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";
        return (User) jdbcTemplate.executeQuery(sql, pstat -> pstat.setString(1, userId), rs -> new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email")));
    }
}
