package com.xunjing.esshs.domain.po;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
@Data
@ConfigurationProperties(prefix = "spring.qq")
public class AdminEmails {
    private final List<String> admin;
}
