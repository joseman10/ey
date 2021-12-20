package com.bank.cl.ey.config.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "generic error")
@Getter
public class GenericException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String message;

    public GenericException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}