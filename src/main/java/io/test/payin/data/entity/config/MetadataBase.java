package io.test.payin.data.entity.config;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class MetadataBase extends EntityBase {

    protected String type;

    protected String value;

    protected String name;

}
