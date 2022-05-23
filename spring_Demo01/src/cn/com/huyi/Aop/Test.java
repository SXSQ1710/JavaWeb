package cn.com.huyi.Aop;

import cn.com.huyi.Aop.AopAnno.Login;
import cn.com.huyi.Aop.AopXML.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @title: Test
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/4/28 19:42
 **/

public class Test {
    @org.junit.Test
    public void testAnno(){
        ApplicationContext context = new ClassPathXmlApplicationContext("AopAnno.xml");
        Login login = context.getBean("Login", Login.class);
        login.add();
    }

    @org.junit.Test
    public void testXML(){
        ApplicationContext context = new ClassPathXmlApplicationContext("AopXML.xml");
        Book book = context.getBean("book", Book.class);
        book.buy();
    }


}
