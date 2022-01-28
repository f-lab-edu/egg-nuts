package me.egg_nuts.user_manager.defined;

import me.egg_nuts.user_manager.common.ConvertForInterface;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Gender implements ConvertForInterface {
    M("M", "남성"),
    F("F", "여성"),
    UNKNOWN("","");


    private String code;
    private String value;

    Gender(String code){
        this.code =code;
    }
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


    public static Gender findEnumByCode(String code) {
        return Arrays.stream(values())
                .filter(e->e.code.equals(code))
                .findAny().orElse(UNKNOWN);
    }


    @Override
    public String getValue() {
        return value;
    }
}
