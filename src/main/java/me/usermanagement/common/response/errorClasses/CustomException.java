package me.usermanagement.common.response.errorClasses;

import me.usermanagement.common.response.messages.error.ErrorCodeDetailEnum;
import me.usermanagement.common.response.messages.error.ErrorMessage;

public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private ErrorCodeDetailEnum errorCodeDetailEnum;

    public CustomException(ErrorMessage errorMessage) {
        super(ErrorCodeDetailEnum.getResponseText(errorMessage));
        this.errorCodeDetailEnum = ErrorCodeDetailEnum.getResponse(errorMessage);
    }

    public ErrorCodeDetailEnum getErrorCodeDetailEnum() {
        return errorCodeDetailEnum;
    }
}
