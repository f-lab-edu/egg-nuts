package me.usermanager.common.response.errorClasses;

import me.usermanager.common.response.messages.error.ErrorMessage;

public class UserException extends CustomException{
    public UserException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
