package me.eggnuts.usermanager.converter;

import me.eggnuts.usermanager.defined.Status;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter extends CommonEnumConverter<Status> {
    StatusConverter() {
        super(Status.class);
    }
}
