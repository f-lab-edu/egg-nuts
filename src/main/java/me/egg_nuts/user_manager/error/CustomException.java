package me.egg_nuts.user_manager.error;

import me.egg_nuts.user_manager.message.ErrorCodeDetailEnum;
import me.egg_nuts.user_manager.message.ErrorMessage;

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
