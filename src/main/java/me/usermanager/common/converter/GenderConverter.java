package me.usermanager.common.converter;

import me.usermanager.common.enums.Gender;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter extends CommonEnumConverter<Gender> {
    GenderConverter() {
        super(Gender.class);
    }
}
