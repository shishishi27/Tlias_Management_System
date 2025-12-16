package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.JobOption;
import org.example.pojo.Result;
import org.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @GetMapping("/empJobCount")
    public Result countEmpByJob() {
        log.info("获取员工职位统计结果");
        JobOption jobOption= reportService.countEmpByJob();
        return Result.success(jobOption);
    }

    //统计员工性别
    @GetMapping("/empGenderCount")
    public Result countEmpByGender() {
        log.info("获取员工性别统计结果");
        List<Map<String, Object>> list = reportService.countEmpByGender();
        return Result.success(list);
    }
}
