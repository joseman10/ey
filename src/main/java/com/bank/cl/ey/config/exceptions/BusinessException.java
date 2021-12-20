package com.bank.cl.ey.config.exceptions;

import com.bank.cl.ey.config.enums.EResponses;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "business error")
@Getter
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1006575901690648984L;
    private final EResponses code;

    public BusinessException(EResponses code) {
        super("El usuario que intenta realizar la peticion no es valido.");
        this.code = code;
    }

    public EResponses getCode() {
        return code;
    }
}
