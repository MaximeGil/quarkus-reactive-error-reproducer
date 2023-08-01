package io.test.payin.core.mapper;

import io.test.payin.data.entity.PayinEntity;
import io.test.payin.core.pojo.payin.record.Payin;
import io.test.payin.core.pojo.payin.record.PayinCreationRequest;
import io.test.payin.core.pojo.payin.record.PayinUpdateRequest;
import jakarta.enterprise.context.ApplicationScoped;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@ApplicationScoped
@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface PayinMapper {

    PayinEntity payinCreationRequestToEntity(PayinCreationRequest payinCreationRequest);

    PayinEntity payinUpdateRequestToEntity(PayinUpdateRequest payinUpdateRequest);

    Payin payinEntityToPayin(PayinEntity payinEntity);
}