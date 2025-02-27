package com.xunjing.esshs.controller;


import com.xunjing.esshs.domain.dto.BookingDto;
import com.xunjing.esshs.domain.po.Result;
import com.xunjing.esshs.service.IBookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 预约表 前端控制器
 * </p>
 *
 * @author author
 * @since 2025-02-23
 */
@RestController
@RequestMapping("/bookings")
@Slf4j
@Tag(name = "预约")
public class BookingController {
    @Resource
    private IBookingService bookingService;
    @PostMapping("/booking")
    @Operation(summary = "预约测试")
    public Result booking(@RequestBody BookingDto bookingDto){
        log.info(String.valueOf(bookingDto));
        return bookingService.booking(bookingDto);
    }
}
