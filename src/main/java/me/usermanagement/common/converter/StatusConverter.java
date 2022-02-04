package me.usermanagement.common.converter;

import me.usermanagement.common.enums.Status;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter extends CommonEnumConverter<Status> {
    StatusConverter() {
        super(Status.class);
    }
}
