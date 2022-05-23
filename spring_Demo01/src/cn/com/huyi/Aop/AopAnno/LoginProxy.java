package cn.com.huyi.Aop.AopAnno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @title: LoginProxy
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/4/28 19:33
 **/

@Component
@Aspect  //生成代理对象
@Order(1)//优先级
public class LoginProxy {
    //相同切入点抽取
    @Pointcut(value = "execution(* cn.com.huyi.Aop.AopAnno.Login.add(..))")
    public void pointdemo(){

    }

    @Before(value = "pointdemo()")
    public void before(){
        System.out.println("before.......");
    }

    @After(value = "execution(* cn.com.huyi.Aop.AopAnno.Login.add(..))")
    public void after(){
        System.out.println("after........");
    }

    @AfterReturning(value = "pointdemo()")
    public void afterReturning(){
        System.out.println("afterReturning........");
    }

    @Around(value = "execution(* cn.com.huyi.Aop.AopAnno.Login.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("aroundBefore........");
        //切入点
        proceedingJoinPoint.proceed();
        System.out.println("aroundAfter........");
    }
}
