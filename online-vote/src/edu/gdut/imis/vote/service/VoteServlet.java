package edu.gdut.imis.vote.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
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
		//TODO
		ServletConfig servletConfig = getServletConfig();
		this.fileName = servletConfig.getInitParameter("fileName");
//		ServletContext Context = this.getServletContext();
//		System.out.println(Context.getRealPath("/"));
		vm = VoteDAO.readVoteModel(fileName);
	}
	@Override
	public void destroy() {
		//TODO
		VoteDAO.writeVoteModel(this.vm,this.fileName);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf8");
		String choice = req.getParameter("choice");
		if("one".equals(choice)){
			vm.setOne(vm.getOne()+1);
		}
		if("two".equals(choice)){
			vm.setTwo(vm.getTwo()+1);
		}
		if("three".equals(choice)){
			vm.setThree(vm.getThree()+1);
		}
		
		PrintWriter out = resp.getWriter();
		
		out.print("<h2>(第一项): <img src='bar.gif' width='"+vm.getOne()*20+"' height='20'/></h2>");
		out.print("<h2>(第二项): <img src='bar.gif' width='"+vm.getTwo()*20+"' height='20'/></h2>");
		out.print("<h2>(第三项): <img src='bar.gif' width='"+vm.getThree()*20+"' height='20'/></h2>");

	}

	
	

}
