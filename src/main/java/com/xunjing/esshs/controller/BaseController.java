package com.xunjing.esshs.controller;

import com.xunjing.esshs.domain.po.Result;
import com.xunjing.esshs.service.BaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "基础接口")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class BaseController {
    private final BaseService baseService;
    @GetMapping("/text")
    @Operation(summary = "测试接口")
    public Result text(){
        return Result.error("测试");
    }
    @GetMapping("/price")
    @Operation(summary = "查询图书价格")
    public Result<String> getPrice(){
        return baseService.getPrice();
    }
    @PutMapping("/price/{price}")
    @Operation(summary = "修改图书价格")
    public Result updatePrice(@PathVariable String price){
        return baseService.updatePrice(price);
    }
}
