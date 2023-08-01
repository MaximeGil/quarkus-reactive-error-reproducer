package io.test.payin.handler.bus;

public interface BusInterface<T> {
    void consumeEvent(T message);
}
