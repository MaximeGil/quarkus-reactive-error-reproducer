package io.test.payin.core.enumeration;

public enum Health {
    ALIVE("Application is alive");

    private final String label;

    Health(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
