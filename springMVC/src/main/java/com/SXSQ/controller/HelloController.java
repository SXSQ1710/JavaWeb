package com.SXSQ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @title: HellowController
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/5/1 11:08
 **/

@Controller
public class HelloController {
    @RequestMapping(value = "/")
    public String index(){
        //返回视图名称
        return "index";
    }

    @RequestMapping(value = "/target")
    public String target(){
        return "target";
    }
}
