package com.xunjing.esshs.domain.dto;

import com.xunjing.esshs.domain.po.Result;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
public class BookingDto {
    @Schema(name = "名字")
    private String name;
    @Schema(name = "电话号码")
    private String phone;
    @Schema(name = "预约开始时间")
    private LocalDateTime beginTime;
    @Schema(name = "预约结束时间")
    private LocalDateTime endTime;
    @Schema(name = "书本重量")
    private Float bookWeight;
    @Schema(name = "地址")
    private String address;
}
