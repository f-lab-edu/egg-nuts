package me.usermanagement.common.converter;


import me.usermanagement.common.response.errorClasses.CustomException;
import me.usermanagement.common.response.messages.error.ErrorMessage;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;

public abstract class CommonEnumConverter <E extends Enum<E> & ConvertForInterface> implements AttributeConverter<E, String> {

    private final Class<E> clz;
    CommonEnumConverter(Class<E> enumClass){
        this.clz = enumClass;
    }

    @Override
    public String convertToDatabaseColumn(E attribute) {
        return attribute.getCode();
    }

    @Override
    public E convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(clz).stream()
                .filter(e->e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(() -> new CustomException(ErrorMessage.UNSUPPORTED_TYPE));
    }
}
