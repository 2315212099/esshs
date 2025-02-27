package com.xunjing.esshs.domain.po;

import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;
    public static <T> Result<T> success(){
        Result<T> result=new Result<>();
        result.code=0;
        return result;
    }
    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.code=0;
        result.data=data;
        return result;
    }
    public static <T> Result<T> error(String msg){
        Result<T> result=new Result<>();
        result.code=1;
        result.message=msg;
        return result;
    }
}
