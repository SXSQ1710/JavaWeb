package cn.com.huyi;

import cn.com.huyi.DAO.factory.DAOFactory;
import cn.com.huyi.common.JDBCUtils;
import cn.com.huyi.entity.User;
import org.junit.Test;

import java.sql.Connection;

/**
 * @title: TestUserDAOImpl
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/3/28 12:07
 **/

public class TestUserDAOImpl {
    @Test
    public void testadminLogin() throws Exception{
        Connection connection = JDBCUtils.getConnection();
        User user1 = new User("1001", "123");
        User user2 = new User("1002", "123");
        User user3 = new User("1001", "1234");
        boolean b1 = DAOFactory.getUserDAO().adminLogin(connection, user1);
        boolean b2 = DAOFactory.getUserDAO().adminLogin(connection, user2);
        boolean b3 = DAOFactory.getUserDAO().adminLogin(connection, user3);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
    }

    @Test
    public void testadminRegist() throws Exception{
        Connection connection = JDBCUtils.getConnection();
        User user1 = new User("1002", "123");
        User user2 = new User("1001", "1234");
        boolean b1 = DAOFactory.getUserDAO().adminRegist(connection, user1);
        boolean b2 = DAOFactory.getUserDAO().adminRegist(connection, user2);
        System.out.println(b1);
        System.out.println(b2);
    }
}
