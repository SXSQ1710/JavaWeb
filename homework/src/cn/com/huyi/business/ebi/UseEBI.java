package cn.com.huyi.business.ebi;

import cn.com.huyi.entity.User;

/**
 * @title: UseEBI
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/3/28 19:58
 **/

public interface UseEBI {
    boolean adminLogin (User user);

    boolean adminRegist (User user);
}
