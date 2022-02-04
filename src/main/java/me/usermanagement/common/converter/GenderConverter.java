package me.usermanagement.common.converter;

import me.usermanagement.common.enums.Gender;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter extends CommonEnumConverter<Gender> {
    GenderConverter() {
        super(Gender.class);
    }
}
