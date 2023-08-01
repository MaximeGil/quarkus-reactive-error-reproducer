package io.test.payin.resource;


import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import io.test.payin.core.pojo.payin.record.PayinUpdateRequest;
import io.test.payin.handler.PayinHandlerService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Path("/api/payins")
@Slf4j
public class PayinResource {

    @Inject
    PayinHandlerService payinHandlerService;

    @PUT
    @WithSession
    public Uni<Response> update(@QueryParam("id") String id,
                                @Valid PayinUpdateRequest payinUpdateRequest) {
        return this.payinHandlerService.update(id, payinUpdateRequest)
                .onItem().transform(payin -> Response.ok(payin).status(Response.Status.OK).build());
    }
}
