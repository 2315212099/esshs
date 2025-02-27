package com.xunjing.esshs.service;

import com.xunjing.esshs.domain.dto.LoginDto;
import com.xunjing.esshs.domain.po.Result;

public interface BaseService {
    Result<String> getPrice();

    Result updatePrice(String price);

    Result login(LoginDto loginDto);
}
