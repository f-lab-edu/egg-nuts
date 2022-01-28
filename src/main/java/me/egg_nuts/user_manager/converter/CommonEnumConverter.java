package me.egg_nuts.user_manager.converter;


import me.egg_nuts.user_manager.common.ConvertForInterface;
import me.egg_nuts.user_manager.error.CustomException;
import me.egg_nuts.user_manager.message.ErrorMessage;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;

public abstract class CommonEnumConverter <E extends Enum<E> & ConvertForInterface> implements AttributeConverter<E, String> {

    private Class<E> clz;

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
