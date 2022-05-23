package com.cn.huyi.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginToDaoImpl implements LoginToDao {

    @Override
    public boolean adminIsReal(int id, int password) throws DaoException {
        String sql = "SELECT * FROM administrators WHERE id =?";
        ResultSet resultSet = null;
        try(Connection conn = getconnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                if (id == resultSet.getInt(1)&& password== resultSet.getInt(2)) return true;
                else return false;
            }else return false;
        }catch (SQLException se){
            se.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean adminRegister(int id, int password) throws DaoException {
        String sql = "INSERT INTO administrators VALUES(?,?)";
        try(Connection conn = getconnection();
            PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1, id);
            pstmt.setInt(2, password);
            if (pstmt.executeUpdate() == 1){
                return true;
            }else {
                return false;
            }
        }catch(SQLException se){
            System.out.println("数据插入失败！");
            return false;
        }
    }
}
