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
		//1.��ȡ��������еĸ����
		//2.�жϴ𰸵ĶԴ��Ƿ�
		//3.������Ӧ��MIME���ͣ���ȡ��Ӧ�����������
		//4.����ɼ�����Ӧ����
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
//		out.write("<script>alert('���ĳɼ���'");
//		out.write(score+")</script>");
//		System.out.println(score);
		//1.��ȡ��������еĸ����
		String answer1 = req.getParameter("question1");
		String answer2 = req.getParameter("question2");
		String[] answer3 = req.getParameterValues("question3");
		String answer4 = req.getParameter("question4");
//2.�жϴ𰸵ĶԴ��Ƿ�
		int score = 0;
		if("3".equals(answer1)) score+=25;
		if("HttpServlet".equals(answer2)) score+=25;
		if("1".equals(answer3[0])) score+=25;
		if("1".equals(answer4)) score+=25;

//3.������Ӧ��MIME���ͣ���ȡ��Ӧ�����������
		resp.setContentType("text/html;charset=UTF-8");

//4.����ɼ�����Ӧ����
		resp.getWriter().write("<script>alert("+score+")</script>");
	}
	
}
