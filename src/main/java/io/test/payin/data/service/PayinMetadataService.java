package io.test.payin.data.service;

import io.smallrye.mutiny.Uni;
import io.test.payin.core.mapper.PayinMetadataMapper;
import io.test.payin.core.pojo.payin.record.PayinMetadata;
import io.test.payin.data.repository.PayinMetadataRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PayinMetadataService {

    @Inject
    PayinMetadataRepository payinMetadataRepository;

    @Inject
    PayinMetadataMapper payinMetadataMapper;

    /**
     * Find a payin metadata by value, meaning by its MangoPay id
     *
     * @param value the payin metadata value to search for
     * @return the found payin metadata
     */
    public Uni<PayinMetadata> findByValue(String value) {
        return this.payinMetadataRepository.findByValue(value)
                .onItem().transform(this.payinMetadataMapper::payinMetadataEntityToPayinMetadata);
    }
}

