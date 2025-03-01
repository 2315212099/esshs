package com.xunjing.esshs;

import com.xunjing.esshs.domain.po.AdminEmails;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan("com.xunjing.esshs.mapper")
@SpringBootApplication
@EnableConfigurationProperties(AdminEmails.class)
@EnableAsync
public class EsshsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsshsApplication.class, args);
    }

}
