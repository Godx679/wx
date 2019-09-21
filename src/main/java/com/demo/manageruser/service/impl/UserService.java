package com.demo.manageruser.service.impl;

import com.demo.manageruser.pojo.User;

import java.util.List;

public interface UserService {
    public List<User> getList();
    public int update(String realname,String password ,Integer id);
    public int addUser(User user);
}
