package io.test.payin.handler;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import io.test.payin.core.pojo.payin.record.PayinEvent;
import io.test.payin.handler.bus.BusInterface;
import io.test.payin.rest.TransferRestService;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.UUID;

@ApplicationScoped
@Slf4j
public class PayinEventHandlerService implements BusInterface<PayinEvent> {

    @RestClient
    TransferRestService transferRestService;

    @ConsumeEvent("SUCCEEDED")
    @Override
    public void consumeEvent(PayinEvent payinEvent) {
        Uni.createFrom().item(payinEvent)
                .onItem().invoke(() -> log.info("🛑 Launching transfers for payin with uuid {}", payinEvent.payinUuid()))
                .onItem().transformToUni(event -> {
                    // To mock
                    //this.transferRestService.launchTransfers(payinEvent.payinUuid();
                    return  Uni.createFrom().item(new Object());
                })
                .subscribe().with(
                        response -> this.printReport(payinEvent.payinUuid()),
                        err -> log.error("🛑 Error occurred while Launching transfers for payin with uuid {}",
                                payinEvent.payinUuid(), err)
                );
    }

    /**
     * Print final log after transfers have been launched
     *
     * @param payinUuid the payin uuid
     */
    private void printReport(UUID payinUuid) {
        log.info("👍 Transfers for payin with uuid {} have been launched. Report to transfer-service for further " +
                "details on each transfer.", payinUuid);
    }
}
