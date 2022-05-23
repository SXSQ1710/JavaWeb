package cn.com.huyi.DAO.impl;

import cn.com.huyi.DAO.dai.BookDAO;
import cn.com.huyi.common.BaseDAO;
import cn.com.huyi.entity.BookModel;

import javax.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * @title: BookDaoImpl
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/3/25 11:13
 **/

@WebServlet("/BookServlet")
public class BookDAOImpl extends BaseDAO<BookModel> implements BookDAO{

    @Override
    public boolean addBook(Connection conn,BookModel bookModel){
        String sql = "INSERT INTO book VALUES(?,?,?,?)";
        try {
//            Connection conn = JDBCUtils.getConnection();
            boolean b = BaseupdateDate(conn, sql, bookModel.getIsbn(), bookModel.getName(), bookModel.getSort(), bookModel.getPrice());
//            JDBCUtils.closeResource(conn);
            return b;
        } catch (Exception e) {
            System.out.println("BookDaoImpl中插入失败！");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBook(Connection conn,String IsbnOrName,boolean choose) {
        String sqlI = "DELETE FROM book WHERE isbn =?";
        String sqlN = "DELETE FROM book WHERE name =?";
        if (choose){
            try {
//                Connection conn = JDBCUtils.getConnection();
                boolean b = BaseupdateDate(conn, sqlI, IsbnOrName);
//                JDBCUtils.closeResource(conn);
                return b;
            } catch (Exception e) {
                System.out.println("BookDaoImpl中增删改失败！");
                e.printStackTrace();
            }
        } else {
            try {
//                Connection conn = JDBCUtils.getConnection();
                boolean b = BaseupdateDate(conn, sqlN, IsbnOrName);
//                JDBCUtils.closeResource(conn);
                return b;
            } catch (Exception e) {
                System.out.println("BookDaoImpl中增删改失败！");
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean updateBook(Connection conn,String IsbnOrName,boolean choose,BookModel bookModel) {
        String sqlI = "UPDATE book SET isbn = ?,name = ?,sort = ?,price = ? WHERE isbn = ?";
        String sqlN = "UPDATE book SET isbn = ?,name = ?,sort = ?,price = ? WHERE name =?";
        if (choose) {
            try {
//                Connection conn = JDBCUtils.getConnection();
                boolean b = BaseupdateDate(conn, sqlI, bookModel.getIsbn(), bookModel.getName(), bookModel.getSort(), bookModel.getPrice(),IsbnOrName);
//                JDBCUtils.closeResource(conn);
                return b;
            } catch (Exception e) {
                System.out.println("BookDaoImpl中增删改失败！");
                e.printStackTrace();
            }
        } else {
            try {
//                Connection conn = JDBCUtils.getConnection();
                boolean b = BaseupdateDate(conn, sqlN, bookModel.getIsbn(), bookModel.getName(), bookModel.getSort(), bookModel.getPrice(),IsbnOrName);
//                JDBCUtils.closeResource(conn);
                return b;
            } catch (Exception e) {
                System.out.println("BookDaoImpl中增删改失败！");
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public BookModel getABook(Connection conn,String IsbnOrName,boolean choose) {
        String sqlI = "SELECT * FROM book WHERE isbn = ?";
        String sqlN = "SELECT * FROM book WHERE name = ?";
        if (choose) {
            try {
//                Connection conn = JDBCUtils.getConnection();
                BookModel bookModel = BasegetDate(conn, sqlI, IsbnOrName);
//                JDBCUtils.closeResource(conn);
                return bookModel;
            } catch (Exception e) {
                System.out.println("BookDaoImpl中增删改失败！");
                e.printStackTrace();
            }
        } else {
            try {
//                Connection conn = JDBCUtils.getConnection();
                BookModel bookModel = BasegetDate(conn, sqlN, IsbnOrName);
//                JDBCUtils.closeResource(conn);
                return bookModel;
            } catch (Exception e) {
                System.out.println("BookDaoImpl中增删改失败！");
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public ArrayList<BookModel> getAllBooks(Connection conn) {
        String sql = "SELECT * FROM book";
        ArrayList<BookModel> books = BasegetDates(conn, sql);
        return books;
    }

}
