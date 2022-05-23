package cn.com.huyi.DAO.impl;

import cn.com.huyi.DAO.dai.UserDAO;
import cn.com.huyi.common.BaseDAO;
import cn.com.huyi.entity.User;

import java.sql.Connection;

/**
 * @title: UserDAOImpl
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/3/28 11:59
 **/

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

    @Override
    public boolean adminLogin(Connection conn , User user) {
        String sql = "SELECT * FROM user WHERE id = ?";
        User rightuser = BasegetDate(conn, sql, user.getId());
        if (rightuser == null) return false;
        else if (rightuser.getPassword().equals(user.getPassword())) return true;
        else return false;
    }

    @Override
    public boolean adminRegist(Connection conn , User user) {
        String sql = "INSERT INTO user VALUES(?,?)";
        boolean b = BaseupdateDate(conn, sql, user.getId(), user.getPassword());
        return b;
    }
}
