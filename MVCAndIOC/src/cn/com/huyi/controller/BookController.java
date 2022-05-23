package cn.com.huyi.controller;

import cn.com.huyi.business.factory.EBIFactory;
import cn.com.huyi.common.ViewBaseServlet;
import cn.com.huyi.entity.BookModel;
import cn.com.huyi.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @title: BookServlet
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/4/3 21:06
 **/

public class BookController{

    /**
     * @Description //TODO 加载添加页面
     * @param request
     * @throws IOException
     */
    protected String add(HttpServletRequest request, HttpServletResponse response){
        ArrayList<String> allSort = BookModel.getAllSort();
        request.setAttribute("AllSort",allSort);
//        super.processTemplate("add",request,response);
        return "add";
    }

    /**
     * @Description //TODO 加载修改页面
     * @param request
     * @throws ServletException
     * @throws IOException
     */
    protected String alet(HttpServletRequest request, HttpServletResponse response){
        ArrayList<String> allSort = BookModel.getAllSort();
        request.setAttribute("AllSort",allSort);
        String isbn = request.getParameter("isbn");
        BookModel aBook = EBIFactory.getBookEBI().getABookByIsbn(isbn);
        request.setAttribute("aBook",aBook);
//        super.processTemplate("alet",request,response);
        return "alet";
    }

    /**
     * @Description //TODO 加载主页面，并刷新
     * @param request
     * @throws IOException
     */
    private String index(HttpServletRequest request, HttpServletResponse response){
        ArrayList<BookModel> allBook = EBIFactory.getBookEBI().getAllBook();
        HttpSession session = request.getSession();
        session.setAttribute("allBook",allBook);
//        super.processTemplate("index",request,response);
        return "index";
    }


    /**
     * @Description //TODO 加载搜索结果页面
     */
    private String search() {
//        super.processTemplate("search",request,response);
        return "search";
    }

    /**
     * @Description //TODO index页面发送处理请求，处理主页面中的搜索功能，搜索结果跳转到search页面
     * @param request
     * @param response
     * @throws IOException
     */
    private String GetABook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String isbnorname = request.getParameter("isbnorname");
        ArrayList<BookModel> aBook = new ArrayList<>();
        if (isbnorname == null){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("<script>alert('请输入正确的信息!')</script>");
            System.out.println("请输入正确的信息!");

        }else if (isbnorname.equals("radioIsbn")){
            String isbn = request.getParameter("isbn");
            BookModel aBookByIsbn = EBIFactory.getBookEBI().getABookByIsbn(isbn);
            if (aBookByIsbn!=null) aBook.add(aBookByIsbn);
            HttpSession session = request.getSession();
            session.setAttribute("aBook",aBook);
//            response.sendRedirect("BookSystem?&operate=search");
            return "search";
        }else if(isbnorname.equals("radioName")){
            String name = request.getParameter("name");
            BookModel aBookByName = EBIFactory.getBookEBI().getABookByName(name);
            if (aBookByName!=null) aBook.add(aBookByName);
            HttpSession session = request.getSession();
            session.setAttribute("aBook",aBook);
//            response.sendRedirect("BookSystem?&operate=search");
            return "search";
        }else {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("<script>alert('请输入正确的信息!')</script>");
            System.out.println("请输入正确的信息!");
        }
        return "error";
    }

    /**
     * @Description //TODO BookSystem.js发送处理请求，处理删除功能,处理完成调整index页面
     * @param request
     * @param response
     * @throws IOException
     */
    private String deletBook(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        String isbn = request.getParameter("isbn");
        boolean b = EBIFactory.getBookEBI().deleteBook(isbn);
        if(b){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("<script>alert('删除成功!')</script>");
            System.out.println("删除成功！");
//            response.sendRedirect("BookSystem?&operate=index");
            return "redirect:BookSystem.do";
        }else {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("<script>alert('删除失败!')</script>");
            System.out.println("删除失败！");
        }
        return "error";
    }

    /**
     * @Description //TODO add页面发送处理请求，处理添加功能，处理完成跳转index页面
     * @param request
     * @param response
     * @throws IOException
     */
    private String addBook(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        String ibsn = request.getParameter("ibsn");
        String name = request.getParameter("name");
        String sort = request.getParameter("sort");
        int price = Integer.parseInt(request.getParameter("price"));
        BookModel book = new BookModel(ibsn, name, sort, price);
        System.out.println(book);
        boolean b = EBIFactory.getBookEBI().addBook(book);
        if(b){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("<script>alert('添加成功!')</script>");
            System.out.println("添加成功！");
//            response.sendRedirect("BookSystem?&operate=index");
            return "redirect:BookSystem.do";
        }else {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("<script>alert('添加失败!')</script>");
            System.out.println("添加失败！");
        }
        return "error";
    }

    /**
     * @Description //TODO alet页面发送处理请求，处理修改功能，跳转index页面
     * @param request
     * @param response
     * @throws IOException
     */
    private String aletBook(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        String ibsn = request.getParameter("ibsn");
        String name = request.getParameter("name");
        String sort = request.getParameter("sort");
        int price = Double.valueOf(request.getParameter("price")).intValue();
        BookModel book = new BookModel(ibsn, name, sort, price);
        System.out.println(book);
        boolean b = EBIFactory.getBookEBI().updateBook(book,ibsn);
        if(b){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("<script>alert('修改成功!')</script>");
            System.out.println("修改成功！");
//            response.sendRedirect("BookSystem?&operate=index");
            return "redirect:BookSystem.do";
        }else {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("<script>alert('修改失败!')</script>");
            System.out.println("修改失败！");
        }
        return "error";
    }


}
