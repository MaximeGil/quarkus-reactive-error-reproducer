package io.test.payin.core.enumeration;

public enum MetadataType {
    MP_USER_LEGAL("Account Legal ID"),
    MP_WALLET("Wallet ID"),
    MP_MANDATE("Mandate ID"),
    MP_PAYIN("Payin ID"),
    MP_KYC("KYC ID"),
    MP_BANK_ACCOUNT("Bank Account ID");

    private final String label;

    MetadataType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
