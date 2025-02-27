package com.xunjing.esshs.domain.dto;

import lombok.Data;

@Data
public class PageDto {
    private Long currentPage;//页码
    private Long size;//一页几个
    private String name;
    private String phone;
}
