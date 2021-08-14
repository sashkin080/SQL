package ru.netology.dataBase;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.BeforeEach;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

    @BeforeEach
    @SneakyThrows
    void setUp() {
        var runner = new QueryRunner();
        var dataSQL = "INSERT INTO users(id, login, password) VALUES (?, ?, ?);";
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "user", "pass");
        ) {
            runner.update(conn, dataSQL);
        }
    }

    public static String userId(String login) throws SQLException {
        var runner = new QueryRunner();
        var userSQL = "SELECT id FROM users WHERE login='" + login + "';";
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "user", "pass");
        ) {
            var userResult = runner.query(conn, userSQL, new ScalarHandler<>());
            String userID = userResult.toString();
            return userID;
        }
    }

    public static String getAuthCode(String login) throws SQLException {
        var runner = new QueryRunner();
        var authSQL = "SELECT code FROM auth_codes WHERE user_id='" + userId(login) + "' ORDER BY id DESC LIMIT 1;";
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "user", "pass");
        ) {
            var authResult = runner.query(conn, authSQL, new ScalarHandler<>());
            String authCode = authResult.toString();
            return authCode;
        }
    }
}


