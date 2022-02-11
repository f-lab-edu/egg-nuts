package me.usermanagement.domain.model;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CommonEntity {
    private ZonedDateTime createAt;
    private ZonedDateTime updateAt;
    @PrePersist
    public void prePersist() {
        this.createAt = ZonedDateTime.now();
        this.updateAt = ZonedDateTime.now();
    }
    @PreUpdate
    public void preUpdate() {
        this.updateAt = ZonedDateTime.now();
    }

}
