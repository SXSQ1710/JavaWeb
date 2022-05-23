<%--
  Created by IntelliJ IDEA.
  User: 胡毅
  Date: 2022/4/27s
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="js/vote.js"></script>
    <title>投票结果</title>
</head>
<body>
<fieldset>
    <legend>感谢<a name="javascript:userName([[${userName}]]);" >${userName}</a>投的一票</legend>
    <h2>(第一项): <img src='bar.gif' width='${vm.getOne() * 20}' height='20'/></h2>
    <h2>(第一项): <img src='bar.gif' width='${vm.getTwo() * 20}' height='20'/></h2>
    <h2>(第一项): <img src='bar.gif' width='${vm.getThree() * 20}' height='20'/></h2>
</fieldset>
</body>
</html>
