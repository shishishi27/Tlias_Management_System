package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Result;
import org.example.utils.AliyunOSSOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliyunOSSOperator AliyunOSSOperator;

    // 上传文件到本地存储
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
//        log.info("接收参数：{}，{}，{}",name, age, file);
//        String originalFilename = file.getOriginalFilename();
//        assert originalFilename != null;
//        String extension= originalFilename.substring(originalFilename.lastIndexOf("."));
//        String newFileName = UUID.randomUUID().toString() + extension;
//        file.transferTo(new File("D:/data/" + newFileName));
//        return Result.success();
//    }

    // 上传文件到阿里云
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("接收参数：{}",file.getOriginalFilename());
        String url=AliyunOSSOperator.uploadFile(file.getBytes(), file.getOriginalFilename());
        return Result.success(url);

    }
}
