package io.test.payin.data.entity;

import io.test.payin.data.entity.config.EntityBase;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "payin")
public class PayinEntity extends EntityBase {

    Integer tips;

    Integer fees;

    Long timestamp;

    @Column(name = "team_uuid")
    UUID teamUuid;

    String status;

    @OneToMany(mappedBy = "payin", cascade = {CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PayinMetadataEntity> metadata;
}
