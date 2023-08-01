package io.test.payin.data.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import io.test.payin.data.entity.PayinMetadataEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PayinMetadataRepository implements PanacheRepository<PayinMetadataEntity> {

    public Uni<PayinMetadataEntity> findByValue(String value) {
        return find("value = ?1", value)
                .firstResult();
    }
}
