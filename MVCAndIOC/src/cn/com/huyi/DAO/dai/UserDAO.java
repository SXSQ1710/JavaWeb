package cn.com.huyi.DAO.dai;

import cn.com.huyi.entity.User;

import java.sql.Connection;

/**
 * @title: UserDAO
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/3/28 11:42
 **/

public interface UserDAO {
    //用户登录
    boolean adminLogin(Connection conn , User user);

    //用户注册
    boolean adminRegist(Connection conn , User user);
}
