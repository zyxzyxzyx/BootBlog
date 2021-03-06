package com.zhang.myblog.repository.impl;



import com.zhang.myblog.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @date 2018/10/23
 * @备注 实现 User各种方法
 *        (已更换)
 **/
//@Repository对应数据访问层Bean ，例如：
//@Repository
public class UserRepositoryImpl {
    //唯一标示id，用于计数。每次递增
    private static AtomicLong counter = new AtomicLong();
    //ConcurrentHashMap它是HashMap的一个线程安全的、支持高效并发的版本
    /**
     * 存储用户信息的容器
     */
    private final ConcurrentMap<Long, User> userMap = new ConcurrentHashMap<>();


    public User saveOrUpdateUser(User user) {
        Long id = user.getId();
        if (id == null) {
            //新建user 还没有id ,以下方法返回递增的数
            id = counter.incrementAndGet();
            user.setId(id);
        }
        this.userMap.put(id, user);
        return user;
    }


    public void deleteUser(Long id) {
        this.userMap.remove(id);
    }


    public User getUserById(Long id) {
        return userMap.get(id);
    }


    public List<User> listUser() {
        //new ArrayList<类型>(包装的数据)
        return new ArrayList<User>(this.userMap.values());
    }
}
