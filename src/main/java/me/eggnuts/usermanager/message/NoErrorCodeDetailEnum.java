package me.eggnuts.usermanager.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;


@AllArgsConstructor
@Getter
public enum NoErrorCodeDetailEnum {

    JOIN_OK(200,NoErrorMessage.JOIN_OK,"회원 가입이 성공했습니다!"),
    UNKNOWN(200,NoErrorMessage.UNKNOWN,""),
    LEAVE_OK(200,NoErrorMessage.LEAVE_OK,"회원 탈퇴처리가 완료됬습니다.");

    private int statusCode;
    private NoErrorMessage noErrorMessage;
    private String responseText;

    public static NoErrorCodeDetailEnum getResponse(NoErrorMessage errorMessage) {
        return Arrays.stream(values())
                .filter(e->e.noErrorMessage.equals(errorMessage))
                .findAny().orElse(UNKNOWN);
    }

    public static String getResponseText(NoErrorMessage errorMessage){
        return Arrays.stream(values())
                .filter(e->e.noErrorMessage.equals(errorMessage))
                .findAny().orElse(UNKNOWN).getResponseText();
    }
}
