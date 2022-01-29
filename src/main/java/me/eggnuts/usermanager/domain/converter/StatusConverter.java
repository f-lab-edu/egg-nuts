package me.eggnuts.usermanager.domain.converter;

import me.eggnuts.usermanager.domain.model.enums.Status;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter extends CommonEnumConverter<Status> {
    StatusConverter() {
        super(Status.class);
    }
}
