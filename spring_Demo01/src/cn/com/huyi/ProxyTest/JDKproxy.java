package cn.com.huyi.ProxyTest;

import cn.com.huyi.Dao.UserDao;
import cn.com.huyi.Dao.UserDaoImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @title: JDKproxy
 * @Author SXSQ
 * @Description //TODO AOP中JDK动态代理
 * @Date 2022/4/27 21:14
 **/

public class JDKproxy {

    public static void main(String[] args) {
        //创建接口实现类代理对象
//        Proxy.newProxyInstance(JDKproxy.class.getClassLoader(), UserDao.class, new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                return null;
//            }
//        })
        //创建接口实现类代理对象
        Class[] classes = {UserDao.class};
        UserDaoImpl userDao = new UserDaoImpl();
        UserDao userProxy = (UserDao) Proxy.newProxyInstance(JDKproxy.class.getClassLoader(), classes, new UserDaoProxy(userDao));
        userProxy.add();
    }

}

class UserDaoProxy implements InvocationHandler{
    private UserDao userDao;

    public UserDaoProxy(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("------调用前置增强方法------");
        Object ret = method.invoke(userDao, args);
        System.out.println("------调用后置增强方法------");
        return ret;
    }
}
