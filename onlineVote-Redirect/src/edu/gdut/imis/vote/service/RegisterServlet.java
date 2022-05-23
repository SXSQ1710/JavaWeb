package edu.gdut.imis.vote.service;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "RegisterServlet", urlPatterns = { "/register" })
@MultipartConfig(location = "D:\\", fileSizeThreshold = 1024)
public class RegisterServlet extends HttpServlet {
	// 返回上传来的文件名
	private String getFilename(Part part) {
		String fname = null;
		// 返回上传的文件部分的content-disposition请求头的值
		String header = part.getHeader("content-disposition");
		System.out.println(header);
		// 返回不带路径的文件名
		fname = header.substring(header.lastIndexOf("=") + 2, header.length() - 1);
		return fname;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 返回Web应用程序文档根目录
		request.setCharacterEncoding("UTF-8");
		String path = this.getServletContext().getRealPath("/images/");
		String username = request.getParameter("name");
		Part p = request.getPart("head_img");
		String message = "";
		if (p.getSize() > 1024 * 1024) { // 上传的文件不能超过1MB大小
			p.delete();
			message = "头像文件太大，不能上传！";
		} else {
			// 文件存储在文档根目录下member子目录中会员号子目录中
			path = path + "/member/" + username;
			File f = new File(path);
			if (!f.exists()) { // 若目录不存在，则创建目录
				f.mkdirs();
			}
			String fname = getFilename(p); // 得到文件名
			System.out.println(fname);
			p.write(path + "\\" + fname); // 将上传的文件写入磁盘
			message = "注册成功！";
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<h1>"+message+"</h1>");
		//后续实现：将用户注册信息写入数据库或者文件之中，跳转到用户个人信息展示页面
	}
}
