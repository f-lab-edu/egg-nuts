package me.egg_nuts.user_manager.entity;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CommonEntity {


    private ZonedDateTime CRATE_AT;
    private ZonedDateTime UPDATE_AT;


    @PrePersist
    public void prePersist(){
        this.CRATE_AT = ZonedDateTime.now();
        this.UPDATE_AT = ZonedDateTime.now();
    }



    @PreUpdate
    public void preUpdate(){
        this.UPDATE_AT = ZonedDateTime.now();
    }

}
