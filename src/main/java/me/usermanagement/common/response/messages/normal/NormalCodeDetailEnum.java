package me.usermanagement.common.response.messages.normal;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;


@AllArgsConstructor
@Getter
public enum NormalCodeDetailEnum {

    JOIN_OK(200, NormalMessage.JOIN_OK,"회원 가입이 성공했습니다!"),
    UNKNOWN(200, NormalMessage.UNKNOWN,""),
    LEAVE_OK(200, NormalMessage.LEAVE_OK,"회원 탈퇴처리가 완료됬습니다.");

    private final int statusCode;
    private final NormalMessage normalMessage;
    private final String responseText;

    public static NormalCodeDetailEnum getResponse(NormalMessage errorMessage) {
        return Arrays.stream(values())
                .filter(e->e.normalMessage.equals(errorMessage))
                .findAny().orElse(UNKNOWN);
    }

    public static String getResponseText(NormalMessage errorMessage){
        return Arrays.stream(values())
                .filter(e->e.normalMessage.equals(errorMessage))
                .findAny().orElse(UNKNOWN).getResponseText();
    }
}
