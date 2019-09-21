package com.demo.manageruser.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }
    //配置一个监控druid的servlet
    @Bean
    public ServletRegistrationBean statSerlvetRegistration(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams=new HashMap<>();
        //监控查看的用户名密码
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","admin");
        initParams.put("allow","");//默认是允许所有访问
        initParams.put("deny","192.168.10.12");//不允许访问
        bean.setInitParameters(initParams);
        return bean;
    }
    //配置一个web监控的filter
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParams=new HashMap<>();
        //监控查看的用户名密码
        initParams.put("exclusions","*.js,*.css,/druid/*");
        initParams.put("loginPassword","admin");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}

