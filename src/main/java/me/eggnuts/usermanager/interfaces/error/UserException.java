package me.eggnuts.usermanager.interfaces.error;

import me.eggnuts.usermanager.interfaces.message.ErrorMessage;

public class UserException extends CustomException{
    public UserException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
