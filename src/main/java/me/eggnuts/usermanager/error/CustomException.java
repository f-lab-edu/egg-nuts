package me.eggnuts.usermanager.error;

import me.eggnuts.usermanager.message.ErrorCodeDetailEnum;
import me.eggnuts.usermanager.message.ErrorMessage;

public class CustomException extends RuntimeException{
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
