package edu.gdut.imis.vote.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.gdut.imis.vote.dao.VoteDAO;
import edu.gdut.imis.vote.model.VoteModel;

/**
 * @author Administrator
 * step1 在初始化方法init()中加载指定文件中的VoteModel实例（历史投票记录）
 * step2 在doPost()方法中，根据提交的投票选项，更新投票统计
 * step3 在destroy()方法中，将统计结果存储到指定的存储文件中
 */
public class VoteServlet extends HttpServlet{
	VoteModel vm = new VoteModel();
	String fileName = "";
	@Override
	public void init() throws ServletException {
		ServletConfig config = this.getServletConfig();
		String initParam = config.getInitParameter("fileName");
		ServletContext context = this.getServletContext();
		fileName = context.getRealPath("/WEB-INF/")+initParam;
		vm = VoteDAO.readVoteModel(fileName);
	}
	@Override
	public void destroy() {
		VoteDAO.writeVoteModel(vm, fileName);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf8");
		String username = null;
		PrintWriter out = resp.getWriter();
		//1.投票前判断用户是否登录，如果用户未登录则跳转到登录页面
		//	1.1 通过getParameter从请求对象中获取用户名
//		String userName = req.getParameter("userName");
		// 1.2 通过getAttribute获取请求对象域空间中的参数
		// 1.3 从Cookie中获取
		Cookie[] cookies = req.getCookies();
		HashMap<String, String> cookieslist = new HashMap<>();
		if(cookies == null) {
			resp.setHeader("refresh", "3;url=/onlineVote/login.html");
			out.print("<h1>请先登录再投票！</h1>");
		}else {
			for (Cookie cookie : cookies){
				cookieslist.put(cookie.getName(),cookie.getValue());
			}
			String userName = cookieslist.get("userName");
			req.getSession().setAttribute("userName",userName);
			String choice = req.getParameter("choice");
			if ("one".equals(choice)) {
				vm.setOne(vm.getOne() + 1);
			}
			if ("two".equals(choice)) {
				vm.setTwo(vm.getTwo() + 1);
			}
			if ("three".equals(choice)) {
				vm.setThree(vm.getThree() + 1);
			}

			req.getSession().setAttribute("vm",vm);
			resp.sendRedirect("result.jsp");
//
//			out.print("<h2>(第一项): <img src='bar.gif' width='" + vm.getOne() * 20 + "' height='20'/></h2>");
//			out.print("<h2>(第二项): <img src='bar.gif' width='" + vm.getTwo() * 20 + "' height='20'/></h2>");
//			out.print("<h2>(第三项): <img src='bar.gif' width='" + vm.getThree() * 20 + "' height='20'/></h2>");
//			out.print("感谢" + userName + "投出的宝贵一票！");
		}
	}

	
	

}
