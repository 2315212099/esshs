package com.xunjing.esshs.config;

import com.xunjing.esshs.domain.po.Result;;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobalExceptionHandler {
   @ExceptionHandler(SQLException.class)
    public Result<String> mysqlHandler(SQLException sqlException){
        return Result.error("数据填写有误");
    }
}
