package cn.com.huyi.Service;

import cn.com.huyi.Dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @title: TestSpring5
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/4/17 20:21
 **/

@Component(value = "userService") //相当于<bean id="userService" class="...">
public class UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(){
//        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
//        User user = context.getBean("user", User.class);
//        System.out.println(user.toString());
        System.out.println("Service Add.....");
        userDao.add();
    }
}
