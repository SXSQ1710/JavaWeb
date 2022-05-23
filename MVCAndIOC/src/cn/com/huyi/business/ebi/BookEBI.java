package cn.com.huyi.business.ebi;

import cn.com.huyi.entity.BookModel;

import java.util.ArrayList;

/**
 * @title: BookEBI
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/3/25 21:00
 **/

public interface BookEBI {
    //增加商品
    boolean addBook(BookModel bookModel);

    //删除商品
    boolean deleteBook(String IsbnOrName);

    //更新商品
    boolean updateBook(BookModel bookModel,String IsbnOrName);

    //查询单条信息
    BookModel getABookByIsbn(String IsbnOrName);

    BookModel getABookByName(String IsbnOrName);

    //查询多条信息
    ArrayList<BookModel> getAllBook();

}
