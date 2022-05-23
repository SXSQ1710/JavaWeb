package cn.com.huyi.Test;

import cn.com.huyi.Config.SpringConfig;
import cn.com.huyi.JODO.User;
import cn.com.huyi.Service.UserService;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @title: TestSpring
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/4/26 20:11
 **/

public class TestSpring {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        UserService user = context.getBean("userService", UserService.class);
        user.add();
    }

    @Test
    public void testSpringConfig(){
        //加载配置类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
//        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        UserService user = context.getBean("userService", UserService.class);
        user.add();
    }
}
