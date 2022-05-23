package gdut.imis.exam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExamServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//TODO
		//1.获取请求对象中的各题答案
		//2.判断答案的对错并记分
		//3.设置响应的MIME类型，获取响应的输出流对象
		//4.输出成绩到响应报文
//		req.setCharacterEncoding("UTF-8");
//		resp.setContentType("text/html;charset=utf-8");
//
//		String question1 = req.getParameter("question1");
////		String question2 = req.getParameter("question2");
////		String question3 = req.getParameter("question3");
////		String question4 = req.getParameter("question4");
//
//		int score = 0;
//		if (question1.equals("3")) score += 25;
////		if (question2.equals("1")) score += 25;
////		if (question3.equals("1")) score += 25;
////		if (question4.equals("1")) score += 25;
//
//		PrintWriter out = resp.getWriter();
//		out.write("<script>alert('您的成绩是'");
//		out.write(score+")</script>");
//		System.out.println(score);
		//1.获取请求对象中的各题答案
		String answer1 = req.getParameter("question1");
		String answer2 = req.getParameter("question2");
		String[] answer3 = req.getParameterValues("question3");
		String answer4 = req.getParameter("question4");
//2.判断答案的对错并记分
		int score = 0;
		if("3".equals(answer1)) score+=25;
		if("HttpServlet".equals(answer2)) score+=25;
		if("1".equals(answer3[0])) score+=25;
		if("1".equals(answer4)) score+=25;

//3.设置响应的MIME类型，获取响应的输出流对象
		resp.setContentType("text/html;charset=UTF-8");

//4.输出成绩到响应报文
		resp.getWriter().write("<script>alert("+score+")</script>");
	}
	
}
