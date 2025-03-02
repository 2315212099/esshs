package com.xunjing.esshs.utils;

import com.xunjing.esshs.domain.po.AdminEmails;
import com.xunjing.esshs.domain.po.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
@RequiredArgsConstructor
@Component
public class SendAdminEmailUtil {
    private final AdminEmails adminEmails;
    private final EmailUtil emailUtil;
    private final DateUtil dateUtil;


    @Async("sendAdminEmailsExecutor")
    public void sendAdminEmails(Booking booking) {
        List<String> admin = adminEmails.getAdmin();
        if(admin.isEmpty()) {
            return;
        }

        String name = booking.getName();
        String phone = booking.getPhone();
        Float weight = booking.getBookWeight();
        LocalDateTime beginTime = booking.getBeginTime();
        LocalDateTime endTime = booking.getEndTime();

        String subject = "预约提醒 - " + name + "同学的二手书回收预约";
        String htmlContent = createHtmlEmail(booking);

        admin.forEach(email -> {
            emailUtil.sendEmail(email, subject, htmlContent, true); // 注意第四个参数true表示HTML格式
        });
    }

    private String createHtmlEmail(Booking booking) {
        String name = booking.getName();
        String phone = booking.getPhone();
        Float weight = booking.getBookWeight();
        String beginTimeStr = dateUtil.getDateTime(String.valueOf(booking.getBeginTime()));
        String endTimeStr = dateUtil.getDateTime(String.valueOf(booking.getEndTime()));
        String address = booking.getAddress() != null ? booking.getAddress() : "未提供";


        // 构建HTML邮件内容
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>预约提醒</title>\n" +
                "    <style>\n" +
                "        body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }\n" +
                "        .container { max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 5px; }\n" +
                "        .header { background-color: #f5f5f5; padding: 10px; border-radius: 5px; margin-bottom: 20px; }\n" +
                "        .header h2 { color: #4a89dc; margin: 0; }\n" +
                "        .content { padding: 0 15px; }\n" +
                "        .info-table { width: 100%; border-collapse: collapse; margin: 15px 0; }\n" +
                "        .info-table td { padding: 8px; border-bottom: 1px solid #eee; }\n" +
                "        .info-table .label { font-weight: bold; width: 30%; }\n" +
                "        .time-block { background-color: #f8f9fa; padding: 10px; border-radius: 4px; margin: 10px 0; }\n" +
                "        .footer { margin-top: 20px; font-size: 12px; color: #999; text-align: center; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <h2>二手书回收预约提醒</h2>\n" +
                "        </div>\n" +
                "        <div class=\"content\">\n" +
                "            <p>您好，管理员：</p>\n" +
                "            <p><b>" + name + "</b> 同学已提交了二手书回收预约申请，详细信息如下：</p>\n" +
                "            \n" +
                "            <table class=\"info-table\">\n" +
                "                <tr>\n" +
                "                    <td class=\"label\">姓名</td>\n" +
                "                    <td>" + name + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td class=\"label\">联系电话</td>\n" +
                "                    <td>" + phone + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td class=\"label\">书本重量</td>\n" +
                "                    <td>" + weight + " kg</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td class=\"label\">地址</td>\n" +
                "                    <td>" + address + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td class=\"label\">期望上门时间</td>\n" +
                "                    <td class=\"time-block\">\n" +
                "                        开始：" + beginTimeStr + "<br>\n" +
                "                        结束：" + endTimeStr + "\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "            </table>\n" +
                "            \n" +
                "            <p>请及时安排回收事宜。</p>\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            <p>此邮件由系统自动发送，请勿直接回复。</p>\n" +
                "            <p>© " + java.time.Year.now().getValue() + " 华水二手书回收系统</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }
}