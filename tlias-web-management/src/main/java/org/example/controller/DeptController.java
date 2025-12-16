package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Dept;
import org.example.pojo.Result;
import org.example.service.DeptService;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//默认/大部分设置beam是单例的，项目启动时自动创建，创建完毕将该bean存入IOC容器
//@Lazy // 延迟到第一次使用的时候创建bean
// bean的线程安全取决于bean的状态和bean的作用域
// 单例，如果是无状态的bean，内部不保存数据、不存在数据共享问题，是线程安全的；
// 如果是有状态的bean，内部会保存状态信息，多个线程同时操作该bean会出现数据不一致问题，线程不安全，
// 若设置为多例，每一次请求作用在全新的bean对象，则线程安全。

//@Scope("prototype") // 多例,
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    private final DeptService deptService;

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

//    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list() {
        log.info("depts list");
        List<Dept> deptList =deptService.findAll();
        return Result.success(deptList);
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id) {
        log.info("depts get: {}", id);
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }

    @DeleteMapping
    public Result delete(Integer id) {
        log.info("depts delete: {}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("depts add: {}", dept);
        deptService.add(dept);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("depts update: {}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
