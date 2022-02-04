package me.usermanagement.common.enums;


import lombok.Getter;
import me.usermanagement.common.converter.ConvertForInterface;

import java.util.Arrays;

@Getter
public enum Status implements ConvertForInterface {
    J("J","가입"),
    L("L","탈퇴"),
    D("D","휴면계정"),
    UNKNOWN("","");

    private final String code;
    private final String value;
    Status(String code,String value){
        this.code = code;
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getCode() {
        return code;
    }

    public static String findValueByCode(String code) {
        return findEnumByCode(code).getValue();
    }

    public static Status findEnumByCode(String code) {
        return Arrays.stream(values())
                .filter(e->e.code.equals(code))
                .findAny().orElse(UNKNOWN);
    }
}
