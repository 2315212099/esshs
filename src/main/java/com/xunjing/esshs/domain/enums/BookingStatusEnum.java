package com.xunjing.esshs.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum BookingStatusEnum {
    COMPLETED("未完成",0), UNCOMPLETED("已完成",1)
    ;

    String data;
    @EnumValue
    @JsonValue
    Integer value;

    BookingStatusEnum(String data, Integer code ) {
        this.data=data;
        this.value=code;
    }
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static BookingStatusEnum of(Integer value){
        if (value == null) {
            return null;
        }
        for (BookingStatusEnum status : values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }

}
