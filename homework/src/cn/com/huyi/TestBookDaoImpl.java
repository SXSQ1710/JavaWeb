package cn.com.huyi;

import cn.com.huyi.DAO.factory.DAOFactory;
import cn.com.huyi.common.JDBCUtils;
import cn.com.huyi.entity.BookModel;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * @title: Test
 * @Author SXSQ
 * @Description //TODO 测试BookDaoImpl
 * @Date 2022/3/25 21:12
 **/

public class TestBookDaoImpl {

    @Test
    public void testaddBook()throws Exception{
        Connection conn = JDBCUtils.getConnection();
        BookModel bookModel1 = new BookModel("4","java","哲学",35);
        BookModel bookModel2 = new BookModel("5", "数据结构", "哲学", 44);
        boolean b1 = DAOFactory.getBookDAO().addBook(conn,bookModel1);
        boolean b2 = DAOFactory.getBookDAO().addBook(conn,bookModel2);
        JDBCUtils.closeResource(conn);
        System.out.println(b1);
        System.out.println(b2);
    }

    @Test
    public void testdeleteBook()throws Exception{
        String isbn = "4";
        String name = "数据结构";
        Connection connection = JDBCUtils.getConnection();
        boolean b1 = DAOFactory.getBookDAO().deleteBook(connection, isbn, true);
        boolean b2 = DAOFactory.getBookDAO().deleteBook(connection, name, false);
        JDBCUtils.closeResource(connection);
        System.out.println(b1);
        System.out.println(b2);
    }

    @Test
    public void testupdateBook()throws Exception{
        String isbn = "4";
        String name = "数据结构";
        BookModel bookModel1 = new BookModel("4","计算机网络","哲学",35);
        BookModel bookModel2 = new BookModel("5", "数据结构", "计算机", 44);
        Connection connection = JDBCUtils.getConnection();
        connection.setAutoCommit(false);
        boolean b1 = DAOFactory.getBookDAO().updateBook(connection,isbn,true,bookModel1);
        boolean b2 = DAOFactory.getBookDAO().updateBook(connection,name,false,bookModel2);
        connection.commit();
        JDBCUtils.closeResource(connection);
        System.out.println(b1);
        System.out.println(b2);
    }

    @Test
    public void testgetBook()throws Exception{
        String isbn = "8";
        String name = "数据结构";
        Connection connection = JDBCUtils.getConnection();
        connection.setAutoCommit(false);
        BookModel bookModel1 =DAOFactory.getBookDAO().getABook(connection,isbn,true);
        BookModel bookModel2 =DAOFactory.getBookDAO().getABook(connection,name,false);
        connection.commit();
        JDBCUtils.closeResource(connection);
        System.out.println(bookModel1);
        System.out.println(bookModel2);
    }

    @Test
    public void testgetBooks()throws Exception{
        Connection connection = JDBCUtils.getConnection();
        connection.setAutoCommit(false);
        ArrayList<BookModel> allBooks = DAOFactory.getBookDAO().getAllBooks(connection);
        connection.commit();
        JDBCUtils.closeResource(connection);
        allBooks.forEach(System.out::println);
    }
}
