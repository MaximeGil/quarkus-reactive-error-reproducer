package io.test.payin.core.pojo.payin.record;

import io.test.payin.core.enumeration.PayinStatus;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record Payin(UUID uuid, Integer tips, Integer fees, Long timestamp, UUID teamUuid, PayinStatus status,
                    List<PayinMetadata> metadata) {
}
