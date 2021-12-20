package com.bank.cl.ey.config.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerRestController {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> exception(Exception exception) {
        log.error("[Controller][Error] exception Ocurrio un error durante la ejecucion: ",
                exception);
        return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Object> businessException(BusinessException exception) {
        log.error("[Controller][Error] exception Ocurrio un error durante la ejecucion: ",
                exception);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(MessageError.builder()
                        .mensaje(exception.getCode().getDescripcion())
                        .build());
    }

    @Builder
    @Data
    static class MessageError {
        private String mensaje;
    }
}

