package com.xunjing.esshs;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.xunjing.esshs.mapper")
@SpringBootApplication
public class EsshsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsshsApplication.class, args);
    }

}
