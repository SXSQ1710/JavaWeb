package cn.com.huyi.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @title: SpringConfig
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/4/26 21:00
 **/

@Configuration //作为配置类，替代xml配置文件
@ComponentScan(basePackages = {"cn.com.huyi"})
public class SpringConfig {
}
