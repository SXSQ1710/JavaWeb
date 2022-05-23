package com.cn.huyi.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface Dao  {
    String url="jdbc:mysql://localhost:3306/takeawaysystem?&useSSL=false&serverTimezone=UTC";
    String user="root";
    String password="13824101958";
    default Connection getconnection() throws DaoException {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throw new DaoException();
        }
    }
}

