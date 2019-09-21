package com.demo.manageruser.dao;

import com.demo.manageruser.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserDao{
    public List<User> getList();
    public int del(Integer id);
    public User getUser(Integer id );
    public int update(String realname,String password ,Integer id);
    public int addUser(User user);
}
