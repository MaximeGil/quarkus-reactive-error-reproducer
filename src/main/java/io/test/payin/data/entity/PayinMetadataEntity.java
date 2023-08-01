package io.test.payin.data.entity;

import io.test.payin.data.entity.config.MetadataBase;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "payin_metadata")
public class PayinMetadataEntity extends MetadataBase {

    @ManyToOne
    private PayinEntity payin;

}
