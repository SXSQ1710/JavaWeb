package cn.com.huyi.Dao;

import org.springframework.stereotype.Repository;

/**
 * @title: UserDaoImpl
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/4/26 20:28
 **/

@Repository
public class UserDaoImpl implements UserDao{
    @Override
    public void add() {
        System.out.println("DAO Add.....");
    }
}
