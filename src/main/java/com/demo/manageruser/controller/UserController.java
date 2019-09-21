package com.demo.manageruser.controller;

import com.demo.manageruser.dao.UserDao;
import com.demo.manageruser.pojo.User;
import com.demo.manageruser.service.UserServiceImpl;
import com.demo.manageruser.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
//    @Autowired
//    JdbcTemplate jdbcTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作的k-v都是字符串
    @Autowired
    RedisTemplate redisTemplate;//操作的k-v都是对象
    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;
    @RequestMapping("/list")
    public String list(Model model){
//        List<Map<String,Object>> users = jdbcTemplate.queryForList("select * from user where isdel =0 ");
        /*
        * 判断缓存是否存在users
        * 如果存在 从缓存里读取 否则从数据库读取*/
//        List<User> users = userDao.getList();
//        redisTemplate.opsForList().leftPush("users",users);
        List<User> users = userService.getList();
        model.addAttribute("users",users);
        return "index";
    }
    @RequestMapping("/delete")
    public String delete(Integer id){
//        String sql = "update user set isdel=1 where id =?";
//        jdbcTemplate.update(sql,new Object[]{id});
        int n = userDao.del(id);
        return "redirect:list";
    }
    @RequestMapping("/toupdate")
    public String toupdate(Integer id,Model model){
//        String sql = "select * from user where id=?";
//        RowMapper<User> user = new BeanPropertyRowMapper<>(User.class);
//        User u = jdbcTemplate.queryForObject(sql,user,id);
        User u =  userDao.getUser(id);
        model.addAttribute("user",u);
        return "update";
    }
    @RequestMapping("/update")
    public String update(Integer id,String realname,String password){
        userService.update(realname,password,id);
        return "redirect:list";
    }
    @RequestMapping("/add")
    public String add(User user){
        userService.addUser(user);
        return "redirect:list";
    }
}
