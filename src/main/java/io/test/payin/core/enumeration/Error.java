package io.test.payin.core.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Error {
    E00001("E00001", "Can't update payin. Payin doesn't exist.");

    @Getter
    private final String code;

    @Getter
    private final String message;

    /**
     * Check if the enum contains the given code
     *
     * @param code the error code to check
     * @return true if the code was found in the enum, false if not
     */
    public static boolean containsCode(String code) {
        for (Error error : Error.values()) {
            if (error.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }
}
