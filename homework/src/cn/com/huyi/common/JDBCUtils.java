package cn.com.huyi.common;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

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
//        Properties properties = new Properties();
//        ClassLoader classLoader = ClassLoader.getSystemClassLoader();//获取系统类加载器
//        InputStream resourceAsStream = classLoader.getResourceAsStream("JDBC.properties");//获取配置文件加载路径
//        properties.load(resourceAsStream);//加载配置文件
//        //从配置文件中获取数据库登录信息
//        String user = properties.getProperty("user");
//        String password = properties.getProperty("password");
//        String url = properties.getProperty("url");
//        String driverClass = properties.getProperty("driverClass");
        Class.forName(driverClass);//加载驱动
        Connection connection = DriverManager.getConnection(url, user, password);//获取连接
        System.out.println("数据库连接已获取:"+connection);
        return connection;
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
