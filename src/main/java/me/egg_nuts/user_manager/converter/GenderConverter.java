package me.egg_nuts.user_manager.converter;

import me.egg_nuts.user_manager.defined.Gender;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter  extends CommonEnumConverter<Gender> {
        GenderConverter() {
                super(Gender.class);
        }
}
