package io.test.payin.core.pojo.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {

    private String code;

    private String message;
}
