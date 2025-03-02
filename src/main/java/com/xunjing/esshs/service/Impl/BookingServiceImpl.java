package com.xunjing.esshs.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunjing.esshs.domain.dto.BookingDto;
import com.xunjing.esshs.domain.dto.PageDto;
import com.xunjing.esshs.domain.enums.BookingStatusEnum;
import com.xunjing.esshs.domain.po.AdminEmails;
import com.xunjing.esshs.domain.po.Booking;
import com.xunjing.esshs.domain.po.Result;
import com.xunjing.esshs.domain.vo.PageVo;
import com.xunjing.esshs.mapper.BookingMapper;
import com.xunjing.esshs.service.IBookingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunjing.esshs.utils.DateUtil;
import com.xunjing.esshs.utils.EmailUtil;
import com.xunjing.esshs.utils.SendAdminEmailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 预约表 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-02-23
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl extends ServiceImpl<BookingMapper, Booking> implements IBookingService {

    private final SendAdminEmailUtil sendAdminEmailUtil;

    @Override
    public Result booking(BookingDto bookingDto) {
        Booking booking = BeanUtil.copyProperties(bookingDto, Booking.class);
        List<Booking> list = lambdaQuery().eq(Booking::getPhone, bookingDto.getPhone()).list();
        if (ObjectUtil.isEmpty(list)) {
            save(booking);
            try {
                sendAdminEmailUtil.sendAdminEmails(booking);
                log.error("异步任务发送邮件成功");
            } catch (Exception e) {
                log.error("异步任务发送邮件失败:{}",e.getMessage());
            }
            return Result.success();
        }
        return Result.error("请勿重复预约");

    }

    @Override
    public Result finishedById(int id) {
        Booking booking = getById(id);
        if (booking == null) return Result.error("预约记录不存在");
        if (booking.getStatus().equals(BookingStatusEnum.UNCOMPLETED)) return Result.error("该预约已被完成");
        booking.setStatus(BookingStatusEnum.UNCOMPLETED);
        updateById(booking);
        return Result.success();
    }

    @Override
    public Result<PageVo<Booking>> pageFind(PageDto pageDto) {
        Long currentPage = pageDto.getCurrentPage();
        Long size = pageDto.getSize();
        String phone = pageDto.getPhone();
        String name = pageDto.getName();

        if (size == null || currentPage == null || size <= 0 || currentPage <= 0)
            return Result.error("分页参数错误");
        Page<Booking> page = this.lambdaQuery().eq(ObjectUtil.isNotEmpty(phone), Booking::getPhone, phone)
                .like(ObjectUtil.isNotEmpty(name), Booking::getName, "%" + name + "%")
                .orderByAsc(Booking::getStatus)
                .page(Page.of(currentPage, size));
        PageVo<Booking> bookingPageVo = new PageVo<>();
        bookingPageVo.setTotal((int) page.getTotal());
        bookingPageVo.setList(page.getRecords());
        return Result.success(bookingPageVo);
    }
}
