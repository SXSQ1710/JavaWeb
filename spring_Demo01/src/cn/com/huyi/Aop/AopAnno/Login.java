package cn.com.huyi.Aop.AopAnno;

import org.springframework.stereotype.Component;

/**
 * @title: Login
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/4/28 19:32
 **/

@Component(value = "Login")
public class Login {
    public void add(){
        System.out.println("Login......");
    }
}
