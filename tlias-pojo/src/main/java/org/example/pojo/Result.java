package org.example.pojo;

import lombok.Data;

@Data
public class Result {
    private Integer code; //编码：1成功0失败
    private String message; //返回结果信息
    private Object data; //返回数据

    public static Result success(){
        Result result = new Result();
        result.code = 1;
        result.message = "success";
        return result;
    }

    public static Result success(Object data){
        Result result = success();
        result.data = data;
        return result;
    }

    public static Result error(String message){
        Result result = new Result();
        result.code = 0;
        result.message = message;
        return result;
    }
}
