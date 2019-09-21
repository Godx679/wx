package com.demo.manageruser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@MapperScan(value = "com.demo.manageruser.dao")
public class ManageruserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageruserApplication.class, args);
    }

}
