package me.usermanagement.common.response.messages.error;

import me.usermanagement.common.response.messages.MessageCommon;

public enum ErrorMessage implements MessageCommon {
    NO_GENDER,
    NO_STATUS,
    UNSUPPORTED_TYPE,
    UNKNOWN,
    UNKNOWN_USER,
    DUPLICATE_ID,
    ID_EMPTY;
}
