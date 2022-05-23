package edu.gdut.imis.vote.service;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(urlPatterns = {"/download"})
public class DownloadServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, 
                HttpServletResponse response)
            		throws ServletException,IOException {
       //正确书写下载文件的路径
        String realPath = request.getServletContext().getRealPath("\\WEB-INF\\recourse\\dance.mp4");
        File file = new File(realPath);
        System.out.println(file.getAbsolutePath());
        if (file.exists()) {
        	//设置文件的MIME类型
           response.setContentType("video/mp4");
            // 设置Content-Disposition响应头，指定文件名
            response.addHeader("Content-Disposition", "attachment;filename=dance.mp4");
            byte[] buffer = new byte[1024];
            try(BufferedInputStream bis = new BufferedInputStream(
            		new FileInputStream(file))) {
                // 返回输出流对象
               OutputStream os = response.getOutputStream();
                // 读取1K的字节
               int i = bis.read(buffer);
               while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
               }
            } catch (IOException ex) {
                System.out.println (ex.toString());
            } 
        }else{
        	response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
    		out.println("文件不存在！");
        }
    }
}

