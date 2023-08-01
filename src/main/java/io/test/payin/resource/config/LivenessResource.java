package io.test.payin.resource.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
public class LivenessResource implements HealthCheck {
    @ConfigProperty(name = "service.name")
    String serviceName;

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.up(serviceName);
    }

}