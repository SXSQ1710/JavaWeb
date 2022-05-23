package cn.com.huyi.DAO.factory;

import cn.com.huyi.DAO.impl.BookDAOImpl;
import cn.com.huyi.DAO.impl.UserDAOImpl;

/**
 * @title: DAOFactory
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/3/25 20:58
 **/

public class DAOFactory {
    public static BookDAOImpl getBookDAO(){
        return new BookDAOImpl();
    }

    public static UserDAOImpl getUserDAO(){
        return new UserDAOImpl();
    }
}
