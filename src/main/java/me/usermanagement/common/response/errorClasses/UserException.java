package me.usermanagement.common.response.errorClasses;

import me.usermanagement.common.response.messages.error.ErrorMessage;

public class UserException extends CustomException {
    public UserException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
