<%--
  Created by IntelliJ IDEA.
  User: 胡毅
  Date: 2022/4/13
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户登录</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
<div id="body">
    <div class=div_login>
        <div id="login-top">
            <h2>用户登录</h2>
            <div id="form">
                <form action="UserController.do" method="post">
                    <input type="hidden" name="operate" value="Login"/>
                    <div id="Text">
                        用户名：<br/>
                        密码：
                    </div>
                    <div id="input">
                        <input type="text" name="username"><br/>
                        <input type="text" name="password"><br/>
                    </div>
                    <div id="ok">
                        <input type="submit" value="登录"/>
                    </div>
                </form>
            </div>
        </div>
        <div id="login-bottom">
            <h3>新用户<<a href="RegisterJsp.jsp">注册</a>></h3>
        </div>
    </div>
</div>
</body>
</html>
