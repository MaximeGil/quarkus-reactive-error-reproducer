package io.test.payin.core.pojo.payin.record;

import io.test.payin.core.enumeration.PayinStatus;
import lombok.Builder;

import java.util.UUID;

@Builder
public record PayinEvent(UUID payinUuid, PayinStatus status) {
}
