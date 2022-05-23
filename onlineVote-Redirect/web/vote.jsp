<%@page language="java" contentType="text/html;charset=utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
    	<meta charset="UTF-8" />
        
        <style type="text/css">
			fieldset{
				width: 35%;
				margin: 0 auto;
			}
			legend{
				font-family : "微软雅黑";
				font-size : 30px;
				color : blue;
			}
        </style>
    </head>
    <body>
       <fieldset>
        <legend>请<%=request.getParameter("userName")%>投一票</legend>   
	   	<form action="/vote" method="post">
	   		<input type="hidden" value="<%=request.getParameter("userName")%>" name="userName"/>
        	<input type="radio" name="choice" value="one" />One<br/>
            <input type="radio" name="choice" value="two" />Two<br/>
            <input type="radio" name="choice" value="three" />Three<br/>
			<br/>
            <input type="submit" value="投票" />
        </form>
      </fieldset>
    </body>
</html>