package io.test.payin.core.pojo.payin.record;

import io.test.payin.core.enumeration.MetadataType;
import lombok.Builder;

@Builder
public record PayinMetadataCreationRequest(MetadataType type, String value, String name,
                                           PayinMetadataPayinCreationRequest payin) {
}
