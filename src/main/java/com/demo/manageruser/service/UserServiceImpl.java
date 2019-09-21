package com.demo.manageruser.service;

import com.demo.manageruser.dao.UserDao;
import com.demo.manageruser.pojo.User;
import com.demo.manageruser.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Cacheable(cacheNames = {"users"})
    public List<User> getList(){
        System.out.println("查询数据库");
        List<User> users = userDao.getList();
        return users;
    }
//    @CachePut(value = "{users}")
    public int update(String realname, String password, Integer id) {
        int n = userDao.update(realname,password,id);
        return n;
    }

//    @CachePut(value = "{users}")
    public int addUser(User user) {
        int n = userDao.addUser(user);
        return n;
    }

}
