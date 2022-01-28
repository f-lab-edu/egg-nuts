package me.egg_nuts.user_manager.error;

import me.egg_nuts.user_manager.message.ErrorMessage;

public class UserException extends CustomException{
    public UserException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
