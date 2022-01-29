package me.eggnuts.usermanager.domain.converter;

import me.eggnuts.usermanager.domain.model.enums.Gender;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter extends CommonEnumConverter<Gender> {
    GenderConverter() {
        super(Gender.class);
    }
}
