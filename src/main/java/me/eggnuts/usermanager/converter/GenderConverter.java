package me.eggnuts.usermanager.converter;

import me.eggnuts.usermanager.defined.Gender;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter extends CommonEnumConverter<Gender> {
    GenderConverter() {
        super(Gender.class);
    }
}
