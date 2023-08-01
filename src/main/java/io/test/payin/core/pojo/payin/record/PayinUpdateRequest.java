package io.test.payin.core.pojo.payin.record;

import io.test.payin.core.enumeration.PayinStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


@Builder
public record PayinUpdateRequest(@NotNull PayinStatus status) {
}
