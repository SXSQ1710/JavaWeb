package cn.com.huyi.business.ebo;

import cn.com.huyi.DAO.factory.DAOFactory;
import cn.com.huyi.business.ebi.BookEBI;
import cn.com.huyi.common.JDBCUtils;
import cn.com.huyi.entity.BookModel;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * @title: BookEBIImpl
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/3/28 20:02
 **/

public class BookEBIImpl implements BookEBI {

    @Override
    public boolean addBook(BookModel bookModel) {
        try {
            Connection connection = JDBCUtils.getConnection();
            boolean b = DAOFactory.getBookDAO().addBook(connection, bookModel);
            JDBCUtils.closeResource(connection);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBook(String IsbnOrName) {
        try {
            Connection connection = JDBCUtils.getConnection();
            boolean b = DAOFactory.getBookDAO().deleteBook(connection, IsbnOrName, true);
            JDBCUtils.closeResource(connection);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBook(BookModel bookModel,String IsbnOrName) {
        try {
            Connection connection = JDBCUtils.getConnection();
            boolean b = DAOFactory.getBookDAO().updateBook(connection, IsbnOrName, true, bookModel);
            JDBCUtils.closeResource(connection);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public BookModel getABookByIsbn(String IsbnOrName) {
        try {
            Connection connection = JDBCUtils.getConnection();
            BookModel aBook = DAOFactory.getBookDAO().getABook(connection, IsbnOrName, true);
            JDBCUtils.closeResource(connection);
            return aBook;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BookModel getABookByName(String IsbnOrName) {
        try {
            Connection connection = JDBCUtils.getConnection();
            BookModel aBook = DAOFactory.getBookDAO().getABook(connection, IsbnOrName, false);
            JDBCUtils.closeResource(connection);
            return aBook;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<BookModel> getAllBook() {
        try {
            Connection connection = JDBCUtils.getConnection();
            ArrayList<BookModel> allBooks = DAOFactory.getBookDAO().getAllBooks(connection);
            JDBCUtils.closeResource(connection);
            return allBooks;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
