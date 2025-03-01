package com.xunjing.esshs.utils;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {
    public String getDateTime(String str){
        String[] ts = str.split("T");
        return ts[0] +" "+ ts[1];
    }
}
