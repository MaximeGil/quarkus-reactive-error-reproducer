package io.test.payin.core.enumeration;

public enum PayinStatus {

    IN_PROGRESS("IN_PROGRESS"),
    ERROR("ERROR"),
    SUCCEEDED("SUCCEEDED");

    private final String label;

    PayinStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
