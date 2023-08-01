package io.test.payin.core.pojo.payin.record;

import io.test.payin.core.enumeration.PayinStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;
import java.util.UUID;


@Builder
public record PayinCreationRequest(@NotNull Integer tips, @NotNull Integer fees, @NotNull Long timestamp,
                                   @NotNull UUID teamUuid, @NotNull PayinStatus status,
                                   List<PayinMetadataCreationRequest> metadata) {
}
