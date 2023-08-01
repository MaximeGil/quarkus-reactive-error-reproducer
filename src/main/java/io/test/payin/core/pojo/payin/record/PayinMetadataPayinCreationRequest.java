package io.test.payin.core.pojo.payin.record;

import lombok.Builder;

import java.util.UUID;

@Builder
public record PayinMetadataPayinCreationRequest(UUID uuid) {
}
