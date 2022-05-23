package cn.com.huyi.business.factory;

import cn.com.huyi.business.ebo.BookEBIImpl;
import cn.com.huyi.business.ebo.UserEBIImpl;

/**
 * @title: DAOfactory
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/3/28 20:05
 **/

public class EBIFactory {
    public static BookEBIImpl getBookEBI(){
        return new BookEBIImpl();
    }

    public static UserEBIImpl getUserEBI(){
        return new UserEBIImpl();
    }
}
