package com.xunjing.esshs.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.xunjing.esshs.domain.dto.LoginDto;
import com.xunjing.esshs.domain.po.Login;
import com.xunjing.esshs.domain.po.Result;
import com.xunjing.esshs.mapper.BaseMapper;
import com.xunjing.esshs.service.BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
@Slf4j
public class BaseServiceImpl implements BaseService {
    private final BaseMapper baseMapper;
    @Override
    public Result<String> getPrice() {
        return Result.success(baseMapper.getPrice());
    }

    @Override
    public Result updatePrice(String price) {
        baseMapper.updatePrice(price);
        return Result.success();
    }

    @Override
    public Result login(LoginDto loginDto) {
        Login login = baseMapper.login(loginDto.getUsername());
        log.info(login.toString());
        if(login==null||!login.getPassword().equals(loginDto.getPassword()))
            return Result.error("瞎登你妈呢！！！");
        return Result.success(login.getUsername());
    }
}
