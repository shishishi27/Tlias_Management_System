package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.EmpService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    private final EmpService empService;
    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping
    public Result list(EmpQueryParam empQueryParam) {
        log.info("分页查询：{}", empQueryParam);
        PageResult<Emp> PageResult = empService.page(empQueryParam);

        return Result.success(PageResult);
    }

    @PostMapping
    public Result add(@RequestBody Emp emp) {
        log.info("新增员工：{}", emp);
        empService.add(emp);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam("ids") List<Integer> ids) {
        log.info("删除员工：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    // 根据id查询员工信息
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id) {
        log.info("查询员工：{}", id);
        Emp emp = empService.findById(id);
        return Result.success(emp);
    }

    //修改员工信息
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工：{}", emp);
        empService.update(emp);
        return Result.success();
    }

}
