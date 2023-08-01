package io.test.payin.data.service;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import io.test.payin.core.mapper.PayinMapper;
import io.test.payin.core.pojo.payin.record.Payin;
import io.test.payin.core.pojo.payin.record.PayinUpdateRequest;
import io.test.payin.data.repository.PayinRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Instant;
import java.util.UUID;

@ApplicationScoped
public class PayinService {

    @Inject
    PayinRepository payinRepository;

    @Inject
    PayinMapper payinMapper;


    @WithTransaction
    public Uni<Payin> update(UUID uuid, PayinUpdateRequest payinUpdateRequest) {
        return this.payinRepository.findByUuid(uuid)
                .onItem().ifNotNull().transform(payinEntity -> {
                    var timestampPlusOneSecond =
                            Instant.ofEpochSecond(payinEntity.getTimestamp()).plusSeconds(1);
                    payinEntity.setTimestamp(timestampPlusOneSecond.getEpochSecond());
                    //payinEntity.setStatus(payinUpdateRequest.status().getLabel());
                    return payinEntity;
                })
                .onItem().transform(payinMapper::payinEntityToPayin);
    }
}
