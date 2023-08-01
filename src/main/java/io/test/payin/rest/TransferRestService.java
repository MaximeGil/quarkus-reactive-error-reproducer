package io.test.payin.rest;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Set;
import java.util.UUID;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@RegisterRestClient(configKey = "transfers-api")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public interface TransferRestService {
    @PUT
    @Path("/launch")
    Uni<Set<Object>> launchTransfers(@QueryParam("payinUuid") UUID payinUuid);


}
