package cn.com.huyi.common;

import cn.com.huyi.common.JDBCUtils;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @title: BaseDAO 通用对数据库增删改查操作
 * @Author SX_SQ
 * @Description //TODO
 * @Date 2022/3/23
 * @param <T> 操作表对应的类型
 * @version2.0 泛型+反射
 **/

public abstract class BaseDAO<T> {

    Class<T> entityClass = null;

    /**
     * @Description 用于获取BaseDAO<T>中T的类型，并将其的运行时类（Class实例）设置为entityClass
     * @explain getClass()获取实际Class对象其实是BaseDAO的子类的Class对象，即各类_____DAOImpl的实例
     *          所以getGenericSuperclass()获取到的是BaseDAO的Class
     *          具体方法用法参考D://javaidea/JDBC/com/huyi/demo02/AllTest中TestGetSuperClass()方法中的说明
     */
    public BaseDAO() {
        Type genericSuperclass = getClass().getGenericSuperclass();//获取运行时类的带有泛型的父类
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;//先将genericSuperclass强转为带有参数的ParameterizedType类型
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();//获取泛型类型
        this.entityClass = (Class<T>) actualTypeArguments[0];//此处可能出问题，可更换为通过class的静态方法获取操作表对应的类型的Class的实例
        //this.entityClass = Class.forName(actualTypeArguments[0].getTypeName())
    }

//    /**
//     * @Description 通过配置文件建立数据库连接 注：配置文件应放于src目录下
//     * @return Connection
//     * @throws Exception
//     */
//    public static Connection getConnection() throws Exception{
//        Properties properties = new Properties();
//        ClassLoader classLoader = ClassLoader.getSystemClassLoader();//获取系统类加载器
//        InputStream resourceAsStream = classLoader.getResourceAsStream("JDBC.properties");//获取配置文件加载路径
//        properties.load(resourceAsStream);//加载配置文件
//        //从配置文件中获取数据库登录信息
//        String user = properties.getProperty("user");
//        String password = properties.getProperty("password");
//        String url = properties.getProperty("url");
//        String driverClass = properties.getProperty("driverClass");
//        Class.forName(driverClass);//加载驱动
//        Connection connection = DriverManager.getConnection(url, user, password);//获取连接
//        System.out.println("数据库连接已获取:"+connection);
//        return connection;
//    }
//
//    /**
//     * @Description 关闭连接、Statement的子接口PreparedStatement和结果集
//     * @param conn 连接
//     * @param ps PreparedStatement的实例
//     * @param resultSet 结果集
//     */
//    public static void closeResource(Connection conn , Statement ps, ResultSet resultSet){
//        try {
//            if (ps!=null)
//                ps.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        try {
//            if (conn!=null) {
//                conn.close();
//                System.out.println("数据库连接已关闭:"+conn);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        try {
//            if (resultSet!=null)
//                resultSet.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    /**
//     * @Description 关闭连接、Statement的子接口PreparedStatement
//     * @param conn 连接
//     * @param ps PreparedStatement的实例
//     */
//    public static void closeResource(Connection conn , Statement ps){
//        try {
//            if (ps!=null)
//                ps.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        try {
//            if (conn!=null) {
//                conn.close();
//                System.out.println("数据库连接已关闭:"+conn);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    /**
//     * @Description 关闭连接
//     * @param conn 连接
//     */
//    public static void closeResource(Connection conn){
//        try {
//            if (conn!=null) {
//                conn.close();
//                System.out.println("数据库连接已关闭:"+conn);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

    /**
     * @Description 通用对数据库进行查询操作 返回多条记录
     * @param conn 连接
     * @param sql 查询语句
     * @param args sal语句中的占位符必须与可变形参数目相同
     * @return 返回查询结果的对象集合
     * @version2.0 考虑事务要求
     */
    protected ArrayList<T> BasegetDates(Connection conn , String sql , Object... args){
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        ArrayList<T> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            resultSet = pstmt.executeQuery();//放回结果集
            ResultSetMetaData rsmd = resultSet.getMetaData();//获取结果集的元数据：ResultSetMetaData
            int columnCount = rsmd.getColumnCount();//通过ResultSetMetaData获取结果集的列数
            while (resultSet.next()){
                T t = entityClass.getDeclaredConstructor().newInstance();//获取运行时类构造器创建实例
                for (int i = 0 ; i < columnCount ; i++){
                    Object columValue = resultSet.getObject(i + 1);
                    String columnName = rsmd.getColumnName(i + 1);//获取每个列的列名
                    Field declaredField = entityClass.getDeclaredField(columnName);//通过反射获取列名对应运行时类中的属性
                    declaredField.setAccessible(true);//给运行时类中对应的属性设置当前属性可访问
                    declaredField.set(t,columValue);//对对象t中的属性进行修改
                }
                list.add(t);
            }
            return list;
        } catch (Exception e){
            System.out.println("BaseDAO中查找失败！");
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null,pstmt,resultSet);//若考虑事务，此处连接不能关
        }
        return null;
    }

    /**
     * @Description 通用对数据库进行特殊值查询操作 返回数值
     * @param conn 连接
     * @param sql 查询语句
     * @param args sal语句中的占位符必须与可变形参数目相同
     * @return 返回特殊值
     * @version2.0 考虑事务要求
     */
    protected <E> E BasegetValue(Connection conn , String sql , Object... args){
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            resultSet = pstmt.executeQuery();
            if (resultSet.next()){
                return (E) resultSet.getObject(1);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            System.out.println("BaseDAO中查找失败！");
            JDBCUtils.closeResource(null,pstmt,resultSet);//若考虑事务，此处连接不能关
        }
        return null;
    }

    /**
     * @Description 通用对数据库进行查询操作 返回单条记录
     * @param conn 连接
     * @param sql 查询语句
     * @param args sal语句中的占位符必须与可变形参数目相同
     * @return 返回查询结果的对象
     * @version2.0 考虑事务要求
     */
    protected T BasegetDate(Connection conn , String sql , Object... args){
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i = 0 ; i < args.length ; i++) {
                pstmt.setObject(i+1,args[i]);
            }
            resultSet = pstmt.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            T t = entityClass.getDeclaredConstructor().newInstance();
            if (resultSet.next()){
                for (int i = 0 ; i < columnCount ; i++){
                    Object columValue = resultSet.getObject(i + 1);
                    String columnName = rsmd.getColumnName(i + 1);//获取每个列的列名
                    Field declaredField = entityClass.getDeclaredField(columnName);//通过反射获取列名对应运行时类中的属性
                    declaredField.setAccessible(true);//给运行时类中对应的属性设置当前属性可访问
                    declaredField.set(t,columValue);//对对象t中的属性进行修改
                }
            }else {
                return null;//TODO 若找不到信息则返回null
            }
            return t;
        } catch (Exception throwables) {
            System.out.println("BaseDAO中查找失败！");
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null,pstmt,resultSet);//若考虑事务，此处连接不能关
        }
        return null;
    }

    /**
     * @Description 通用对数据库进行增删改操作
     * @param sql sql语句
     * @param args sal语句中的占位符必须与可变形参数目相同
     * @version2.0 考虑事务要求
     */
    protected  boolean BaseupdateDate(Connection conn,String sql , Object... args){
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);//2.预编译sql语句，返回PreparaedStatement实例
            //3.填充占位符
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            //4.执行
            if (pstmt.executeUpdate() == 1){
                return true;
            }else {
                return false;
            }
        } catch (Exception e){
            System.out.println("BaseDAO中增删改失败！");
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null,pstmt);//若考虑事务，此处连接不能关
        }
        return false;
    }
}
