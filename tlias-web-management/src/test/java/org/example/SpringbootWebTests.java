package org.example;

import com.google.gson.Gson;
import org.example.pojo.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class SpringbootWebTests {
    @Autowired
    private ApplicationContext applicationContext; // 获取IOC容器

    @Autowired
    private Gson gson;

    @Test
    public void testScope() {
        for (int i = 0; i < 10; i++) {
            System.out.println(applicationContext.getBean("deptController"));
        }
    }

    @Test
    public void testGson() {
        System.out.println(gson.toJson(Result.success("hello world")));
    }


}
