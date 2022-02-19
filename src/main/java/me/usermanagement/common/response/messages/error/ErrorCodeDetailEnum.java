package me.usermanagement.common.response.messages.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;


@AllArgsConstructor
@Getter
public enum ErrorCodeDetailEnum {
    NO_GENDER(400, ErrorMessage.NO_GENDER, "성별을 확인해주세요"),
    NO_STATUS(400, ErrorMessage.NO_STATUS, "회원 상태코드를 확인해주세요"),
    UNSUPPORTED_TYPE(400, ErrorMessage.UNSUPPORTED_TYPE, "지원하지 않는 타입 입니다"),
    UNKNOWN_USER(404, ErrorMessage.UNKNOWN_USER, "존재하지 않는 사용자 입니다. "),
    DUPLICATE_USER(400, ErrorMessage.DUPLICATE_ID, "이미 등록된 아이디 입니다. 다른 아이디를 사용해주세요"),
    ID_EMPTY(400, ErrorMessage.ID_EMPTY, "아이디 항목이 비어있습니다"),
    NAME_EMPTY(400, ErrorMessage.NAME_EMPTY, "이름 항목이 비어있습니다"),
    INVALID_PARAMETER(400, ErrorMessage.INVALID_PARAMETER, "유효하지 않은 값을 입력하셨습니다"),
    UNKNOWN(400, ErrorMessage.UNKNOWN, "알수 없는 오류입니다. 담당자에게 문의해주세요");

    private final int statusCode;
    private final ErrorMessage errorMessage;
    private final String responseText;

    public static ErrorCodeDetailEnum getResponse(ErrorMessage errorMessage) {
        return Arrays.stream(values())
                .filter(e -> e.errorMessage.equals(errorMessage))
                .findAny().orElse(UNKNOWN);
    }

    public static String getResponseText(ErrorMessage errorMessage) {
        return Arrays.stream(values())
                .filter(e -> e.errorMessage.equals(errorMessage))
                .findAny().orElse(UNKNOWN).getResponseText();
    }
}
