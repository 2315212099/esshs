package com.xunjing.esshs.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageVo<T> {
    private int total;
    private List<T> list;
}
