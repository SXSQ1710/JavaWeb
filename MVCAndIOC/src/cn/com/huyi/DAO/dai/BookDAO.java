package cn.com.huyi.DAO.dai;

import cn.com.huyi.entity.BookModel;

import java.sql.Connection;
import java.util.List;

/**
 * @title: BookDAO
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/3/25 11:56
 **/

public interface BookDAO {
    //增加商品
    boolean addBook(Connection conn, BookModel bookModel);

    //删除商品
    boolean deleteBook(Connection conn,String IsbnOrName,boolean choose);

    //更新商品
    boolean updateBook(Connection conn,String IsbnOrName,boolean choose,BookModel bookModel);

    //查询单条信息
    BookModel getABook(Connection conn,String IsbnOrName,boolean choose);

    //查询多条信息
    List<BookModel> getAllBooks(Connection conn);
}
