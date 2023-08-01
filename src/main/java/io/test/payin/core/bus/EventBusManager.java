package io.test.payin.core.bus;

import io.test.payin.core.pojo.payin.record.PayinEvent;
import io.vertx.mutiny.core.eventbus.EventBus;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EventBusManager {
    @Inject
    EventBus eventBus;

    public void sendMessage(PayinEvent payinEvent) {
        if (payinEvent != null && payinEvent.status() != null) {
            eventBus.send(payinEvent.status().getLabel(), payinEvent);
        }
    }
}
