package me.eggnuts.usermanager.defined;

import me.eggnuts.usermanager.common.ConvertForInterface;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Status implements ConvertForInterface {
    J("J","가입"),
    L("L","탈퇴"),
    D("D","휴면계정"),
    UNKNOWN("","");

    private String code;
    private String value;
    Status(String code,String value){
        this.code = code;
        this.value = value;
    }

    Status(String code){
        this.code = code;
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
