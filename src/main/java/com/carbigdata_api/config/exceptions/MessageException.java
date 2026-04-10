package com.carbigdata_api.config.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageException {

    private String message;
    private String field;

    public MessageException(String message) {
        this.message = message;
    }
}
