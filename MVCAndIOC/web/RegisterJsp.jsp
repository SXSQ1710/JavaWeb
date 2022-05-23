<%--
  Created by IntelliJ IDEA.
  User: 胡毅
  Date: 2022/4/13
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户注册</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
<div id="body">
    <div class=div_login>
        <div id="login-top">
            <h2>用户注册</h2>
            <div id="form">
                <form action="UserController.do" method="post">
                    <input type="hidden" name="operate" value="Regist"/>
                    <div id="Text">
                        用户名：<br/>
                        密码：
                    </div>
                    <div id="input">
                        <input type="text" value="User ID" name="username"><br/>
                        <input type="text" value="password" name="password"><br/>
                    </div>
                    <div id="ok">
                        <input type="submit" value="注册"/>
                    </div>
                </form>
            </div>
        </div>
        <div id="login-bottom">
            <h3>用户<a href="LoginJsp.jsp">登录</a></h3>
        </div>
    </div>
</div>
</body>
</html>
