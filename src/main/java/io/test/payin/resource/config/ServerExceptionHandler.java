package io.test.payin.resource.config;

import io.smallrye.mutiny.Uni;
import io.test.payin.core.enumeration.Error;
import io.test.payin.core.pojo.error.ErrorResponse;
import io.vertx.ext.web.handler.HttpException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

@ApplicationScoped
@Slf4j
public class ServerExceptionHandler {

    @ConfigProperty(name = "service.name")
    String serviceName;

    @ServerExceptionMapper
    public Uni<Response> httpExceptionHandler(HttpException httpException) {
        return Uni.createFrom()
                .item(Response.status(httpException.getStatusCode(), httpException.getMessage()).build());
    }

    @ServerExceptionMapper
    public Uni<Response> uncaughtExceptionHandler(Exception exception) {
        // If the exception is handled in the code using the Error enum, it will contain an error code
        // In that case, retrieve the code and instantiate an ErrorResponse
        // Otherwise, just retrieve the exception message
        Object entity;
        if (Error.containsCode(exception.getMessage())) {
            Error error = Error.valueOf(exception.getMessage());
            entity = new ErrorResponse(error.getCode(), error.getMessage());
        } else {
            log.error("Exception with unknown error code occurred with message {}", exception.getLocalizedMessage(), exception);
            entity = new ErrorResponse("E0000", exception.getMessage());
        }

        if (exception instanceof NotFoundException) {
            return Uni.createFrom()
                    .item(Response.status(Response.Status.NOT_FOUND.getStatusCode())
                            .entity(entity).build());
        } else if (exception instanceof BadRequestException) {
            return Uni.createFrom()
                    .item(Response.status(Response.Status.BAD_REQUEST.getStatusCode())
                            .entity(entity).build());
        } else if (exception instanceof InternalServerErrorException) {
            return Uni.createFrom()
                    .item(Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
                            .entity(entity).build());
        }

        return Uni.createFrom()
                .item(Response.status(Response.Status.SERVICE_UNAVAILABLE.getStatusCode())
                        .entity(entity).build());
    }
}
