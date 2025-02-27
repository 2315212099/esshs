package com.xunjing.esshs.service;

import com.xunjing.esshs.domain.dto.BookingDto;
import com.xunjing.esshs.domain.dto.PageDto;
import com.xunjing.esshs.domain.po.Booking;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xunjing.esshs.domain.po.Result;
import com.xunjing.esshs.domain.vo.PageVo;

/**
 * <p>
 * 预约表 服务类
 * </p>
 *
 * @author author
 * @since 2025-02-23
 */
public interface IBookingService extends IService<Booking> {

    Result booking(BookingDto bookingDto);

    Result finishedById(int id);

    Result<PageVo<Booking>> pageFind(PageDto pageDto);
}
