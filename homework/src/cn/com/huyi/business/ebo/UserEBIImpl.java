package cn.com.huyi.business.ebo;

import cn.com.huyi.DAO.factory.DAOFactory;
import cn.com.huyi.business.ebi.UseEBI;
import cn.com.huyi.common.JDBCUtils;
import cn.com.huyi.entity.User;

import javax.print.DocFlavor;
import java.sql.Connection;

/**
 * @title: UserEbiImpl
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/3/28 20:00
 **/

public class UserEBIImpl implements UseEBI {
    @Override
    public boolean adminLogin(User user) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            boolean b = DAOFactory.getUserDAO().adminLogin(connection, user);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection);
        }
        return false;
    }

    @Override
    public boolean adminRegist(User user) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            boolean b = DAOFactory.getUserDAO().adminRegist(connection, user);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            JDBCUtils.closeResource(connection);
        }
        return false;
    }


}
