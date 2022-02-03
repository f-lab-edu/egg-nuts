package me.usermanager.common.converter;

import me.usermanager.common.enums.Status;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter extends CommonEnumConverter<Status> {
    StatusConverter() {
        super(Status.class);
    }
}
