package io.test.payin.data.entity.config;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Data;

import java.util.UUID;

@MappedSuperclass
@Data
public abstract class EntityBase extends PanacheEntityBase {

    @Id
    @Column(unique = true)
    protected UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @PrePersist
    public void uuidGenerator() {
        this.setUuid(UUID.randomUUID());
    }

}
