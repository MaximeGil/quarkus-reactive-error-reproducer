package io.test.payin.core.mapper;

import io.test.payin.data.entity.PayinMetadataEntity;
import io.test.payin.core.pojo.payin.record.PayinMetadata;
import io.test.payin.core.pojo.payin.record.PayinMetadataCreationRequest;
import jakarta.enterprise.context.ApplicationScoped;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@ApplicationScoped
@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface PayinMetadataMapper {
    PayinMetadata payinMetadataEntityToPayinMetadata(PayinMetadataEntity payinMetadataEntity);
}