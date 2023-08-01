package io.test.payin.core.pojo.payin.record;

import io.test.payin.core.enumeration.MetadataType;
import lombok.Builder;

import java.util.UUID;

@Builder
public record PayinMetadata(UUID uuid, MetadataType type, String value, String name, PayinMetadataPayin payin) {
}
