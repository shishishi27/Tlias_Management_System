package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Emp;
import org.example.pojo.LogInfo;
import org.example.pojo.Result;
import org.example.service.EmpService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    private final EmpService empService;

    public LoginController(EmpService empService) {
        this.empService = empService;
    }

    //员工登录
    @PostMapping
    public Result login(@RequestBody Emp emp) {
        log.info("员工登录：{}", emp);
        LogInfo logInfo = empService.login(emp);
        if (logInfo == null) {
            return Result.error("用户名或密码错误");
        }
        return Result.success(logInfo);
    }
}
