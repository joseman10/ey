package com.bank.cl.ey.config.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public enum EResponses {
    EMAIL_INVALID(100, "Email no valido formato @dominio.cl"),
    EMAIL_EXIST(100, "Email ya existe"),
    PASS_INVALID(100, "Inválido password, debe contener una Mayuscula, letras minúsculas, y dos numeros");
    private final int code;
    private final String descripcion;
}
