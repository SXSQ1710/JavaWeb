package edu.gdut.imis.vote.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf8");
		String uName = req.getParameter("userName");
		String pwd = req.getParameter("passWord");
		PrintWriter out = resp.getWriter();
		ServletContext context = this.getServletContext();
		//1.从ServletContext对象中获取存放用户信息的Map实例赋予Map<String,String> ul
		Map<String,String> ul = (Map<String, String>) context.getAttribute("userList");
		if(ul.get(uName) != null) {
			if(ul.get(uName).equals(pwd)) {
				//2：验证通过则跳转到投票页面vote.jsp
				//		2.1 用重定向的方式
//				resp.sendRedirect("/onlineVote-Redirect/vote.jsp?userName="+uName);
				//		2.2 用请求转发的方式
				Cookie userCookie = new Cookie("userName", uName);
				userCookie.setMaxAge(60*60);
				resp.addCookie(userCookie);
				resp.sendRedirect("vote.html");

			}else {
				resp.setHeader("refresh", "3;url=/onlineVote/login.html");
				out.print("<h1>密码错误!等待3秒后跳转到登录页面……</h1>");
			}
		}else {
			resp.setHeader("refresh", "5;url=/onlineVote/login.html");
			out.print("<h1>用户名不存在!等待5秒后跳转到登录页面……</h1>");
		}
	}

	@Override
	public void init() throws ServletException {
		//1.从ServletConfig实例中获取Servlet初始参数中的userFile
		ServletConfig config = this.getServletConfig();
		String userFile = config.getInitParameter("userFile");
		//2.用ServletContext提供的getResourceAsStream方法从资源文件获得输入流，转换为字符流并缓冲    BufferedReader br
		InputStream in = this.getServletContext().getResourceAsStream(userFile);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String str;
		HashMap<String,String> ul = new HashMap<String,String>();
		try {
			while((str = br.readLine())!=null) {
				String[] user = str.split("/");
				ul.put(user[0], user[1]);
			}
			//3. 将Map的实例ul写入ServletContext对象的域空间
			this.getServletContext().setAttribute("userList",ul);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
