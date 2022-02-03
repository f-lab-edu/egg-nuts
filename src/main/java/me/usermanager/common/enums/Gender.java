package me.usermanager.common.enums;

import me.usermanager.common.converter.ConvertForInterface;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Gender implements ConvertForInterface {
    M("M", "남성"),
    F("F", "여성"),
    UNKNOWN("", "");

    private final String code;
    private final String value;

    Gender(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return code;
    }
    public static String findValueByCode(String code) {
        return findEnumByCode(code).getValue();
    }
    @Override
    public String getValue() {
        return value;
    }

    public static Gender findEnumByCode(String code) {
        return Arrays.stream(values())
                .filter(e -> e.code.equals(code))
                .findAny().orElse(UNKNOWN);
    }
}
