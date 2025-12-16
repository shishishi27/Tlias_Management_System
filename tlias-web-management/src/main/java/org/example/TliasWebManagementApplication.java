package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@EnableHeaderConfig  // 第三方提供@EnableXXX(封装Import注解)，自动装配
@ServletComponentScan  // 扫描web组件Servlet、Filter、Listener
@SpringBootApplication // 扫描包及其子包下的组件
public class TliasWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TliasWebManagementApplication.class, args);
    }

}
