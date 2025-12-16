package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//封装登陆结果
public class LogInfo {
    private Integer id;
    private String username;
    private String name;
    private String token;

}
