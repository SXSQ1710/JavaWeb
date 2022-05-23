package com.cn.huyi.Dao;

public interface LoginToDao extends Dao{
    boolean adminIsReal(int id, int password) throws DaoException;
    boolean adminRegister(int id, int password) throws DaoException;
}
