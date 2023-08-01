package io.test.payin.data.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.test.payin.data.entity.PayinEntity;
import io.test.payin.core.enumeration.PayinStatus;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
@Slf4j
public class PayinRepository implements PanacheRepository<PayinEntity> {
    public Uni<PayinEntity> findByUuid(UUID uuid) {
        return find("uuid = ?1", uuid)
                .firstResult();
    }

    public Uni<PayinEntity> findLatestByStatusByTeamUuidAndStatus(UUID teamUuid, PayinStatus payinStatus) {
        return find("teamUuid = ?1 AND status = ?2", Sort.by("timestamp").descending(), teamUuid, payinStatus.getLabel())
                .firstResult();
    }

    public Multi<PayinEntity> findAllLatestByTeamUuidAndStatusAndTimestamps(UUID teamUuid, PayinStatus payinStatus,
                                                                            Long minTimestamp, Long maxTimestamp) {
        return find("teamUuid = ?1 AND status = ?2 AND timestamp >= ?3 AND timestamp < ?4",
                Sort.by("timestamp").descending(), teamUuid, payinStatus.getLabel(), minTimestamp, maxTimestamp)
                .list().toMulti().flatMap(payinEntities -> Multi.createFrom().iterable(payinEntities));
    }

    public Multi<PayinEntity> findByQueryParameters(int pageIndex, int pageSize, Map<String, Object> queryParameters) {
        String query = this.generateQueryString(queryParameters);
        return find(query, queryParameters)
                .page(pageIndex, pageSize)
                .list().toMulti().flatMap(payinEntities -> Multi.createFrom().iterable(payinEntities));
    }

    private String generateQueryString(Map<String, Object> queryParameters) {
        AtomicReference<String> query = new AtomicReference<>("");
        queryParameters.forEach((key, value) -> {
            if (!query.get().isBlank() && !query.get().isEmpty()) {
                query.set(query.get().concat(" and "));
            }
            if (key.equals("beginTimestamp")) {
                query.set(query.get().concat("timestamp").concat(" >= ").concat(":").concat(key));
            } else if (key.equals("endTimestamp")) {
                query.set(query.get().concat("timestamp").concat(" <= ").concat(":").concat(key));
            } else {
                query.set(query.get().concat(key).concat(" = ").concat(":").concat(key));
            }
        });

        return query.get();
    }

    public Multi<PayinEntity> findAll(int pageIndex, int pageSize) {
        return findAll()
                .page(pageIndex, pageSize)
                .list().toMulti().flatMap(payinEntities -> Multi.createFrom().iterable(payinEntities));
    }
}
