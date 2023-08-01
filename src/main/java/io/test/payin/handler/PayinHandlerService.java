package io.test.payin.handler;

import io.smallrye.mutiny.Uni;
import io.test.payin.core.pojo.payin.record.Payin;
import io.test.payin.core.pojo.payin.record.PayinEvent;
import io.test.payin.core.pojo.payin.record.PayinUpdateRequest;
import io.test.payin.data.service.PayinMetadataService;
import io.test.payin.data.service.PayinService;
import io.test.payin.core.bus.EventBusManager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@ApplicationScoped
@Slf4j
public class PayinHandlerService extends EventBusManager {

    @Inject
    PayinService payinService;

    @Inject
    PayinMetadataService payinMetadataService;


    public Uni<Payin> update(String mangoPayPayinId, PayinUpdateRequest payinUpdateRequest) {
        return this.payinMetadataService.findByValue(mangoPayPayinId)
                .onItem().ifNotNull().transformToUni(payinMetadata ->
                        this.payinService.update(payinMetadata.payin().uuid(), payinUpdateRequest))
                .onItem().ifNotNull().invoke(updatedPayin -> this.sendPayinEvent(updatedPayin.uuid(), payinUpdateRequest))
                .onItem().ifNull().failWith(new BadRequestException("DD"));
    }


    private void sendPayinEvent(UUID uuid, PayinUpdateRequest payinUpdateRequest) {
        PayinEvent payinEvent = PayinEvent.builder()
                .payinUuid(uuid)
                .status(payinUpdateRequest.status())
                .build();

        this.sendMessage(payinEvent);
    }
}
