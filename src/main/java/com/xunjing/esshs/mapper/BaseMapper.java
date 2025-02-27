package com.xunjing.esshs.mapper;

import com.xunjing.esshs.domain.dto.LoginDto;
import com.xunjing.esshs.domain.po.Login;

public interface BaseMapper {
    public String getPrice();
    public void updatePrice(String price);
    public Login login(String username);
}
