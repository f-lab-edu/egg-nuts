package me.egg_nuts.user_manager.converter;

import me.egg_nuts.user_manager.defined.Status;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter extends CommonEnumConverter<Status> {
        StatusConverter() {
                super(Status.class);
        }
}
