package cn.com.huyi.common;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.print.DocFlavor;
import javax.sql.DataSource;
import java.sql.*;

/**
 * @title: JDBCUtils
 * @Author SX_SQ
 * @Description //TODO 操作数据库的工具类
 * @Date 2022/3/20 15:43
 **/

public class JDBCUtils {

    /**
     * @Description 通过配置文件建立数据库连接 注：配置文件应放于src目录下
     * @return Connection
     * @explain TODO 数据库连接信息请到src目录下的JDBC.properties配置文件中更改
     * @throws Exception
     */

    public static Connection getConnection() throws Exception{
        String url = "jdbc:mysql://localhost:3306/booksystem?&useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "13824101958";
        String driverClass = "com.mysql.cj.jdbc.Driver";
        Class.forName(driverClass);//加载驱动
        Connection connection = DriverManager.getConnection(url, user, password);//获取连接
        System.out.println("数据库连接已获取:"+connection);
        return connection;
    }

    public static Connection getCon2() throws Exception {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource)
                envCtx.lookup("jdbc/webDS");
        return ds.getConnection();
    }

    /**
     * @Description 关闭连接、Statement的子接口PreparedStatement和结果集
     * @param conn 连接
     * @param ps PreparedStatement的实例
     * @param resultSet 结果集
     */
    public static void closeResource(Connection conn , Statement ps, ResultSet resultSet){
        try {
            if (ps!=null)
                ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (conn!=null) {
                conn.close();
                System.out.println("数据库连接已关闭:"+conn);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (resultSet!=null)
                resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @Description 关闭连接、Statement的子接口PreparedStatement
     * @param conn 连接
     * @param ps PreparedStatement的实例
     */
    public static void closeResource(Connection conn , Statement ps){
        try {
            if (ps!=null)
                ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (conn!=null) {
                conn.close();
                System.out.println("数据库连接已关闭:"+conn);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @Description 关闭连接
     * @param conn 连接
     */
    public static void closeResource(Connection conn){
        try {
            if (conn!=null) {
                conn.close();
                System.out.println("数据库连接已关闭:"+conn);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
