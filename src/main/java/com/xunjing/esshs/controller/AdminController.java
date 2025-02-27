package com.xunjing.esshs.controller;

import com.xunjing.esshs.domain.dto.BookingDto;
import com.xunjing.esshs.domain.dto.LoginDto;
import com.xunjing.esshs.domain.dto.PageDto;
import com.xunjing.esshs.domain.po.Booking;
import com.xunjing.esshs.domain.po.Result;
import com.xunjing.esshs.domain.vo.PageVo;
import com.xunjing.esshs.service.BaseService;
import com.xunjing.esshs.service.IBookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "管理员接口")
public class AdminController {
    private final BaseService baseService;
    private final IBookingService bookingService;
    @PostMapping("/login")
    @Operation(summary = "管理员登录")
    public Result login(@RequestBody LoginDto loginDto){
        return baseService.login(loginDto);
    }
    @PostMapping("/booking/pages")
    @Operation(summary = "分页查询预约记录")
    private Result<PageVo<Booking>> pageFind(@RequestBody PageDto pageDto){
        return bookingService.pageFind(pageDto);
    }
    @DeleteMapping("/del/{id}")
    @Operation(summary = "删除预约记录")
    public Result delBookingById(@PathVariable List<String> id){
        bookingService.removeByIds(id);
        return Result.success();
    }
    @GetMapping("/finished/{id}")
    @Operation(summary = "完成预约")
    public Result finishedById(@PathVariable int id){
        return bookingService.finishedById(id);
    }
}
