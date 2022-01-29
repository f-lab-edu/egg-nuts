package me.eggnuts.usermanager.error;

import me.eggnuts.usermanager.message.ErrorMessage;

public class UserException extends CustomException{
    public UserException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
