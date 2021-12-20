package com.bank.cl.ey.adapter.controller;

import com.bank.cl.ey.adapter.controller.model.inbound.UserInfo;
import com.bank.cl.ey.adapter.controller.model.outbound.UserDTO;
import com.bank.cl.ey.domain.usecase.UserCreateUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${application.client.api.path}" +
        "/users")
@Slf4j
@Tag(name = "users", description = "Busqueda de Productos en Walmart")
@RequiredArgsConstructor
public class UserController {

    private final UserCreateUC userCreateUC;

    @Operation(summary = "Crear usuarios", description =
            "Controlador para registrar usuarios",
            tags = {"products"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación de " +
                    "registro completado"),
            @ApiResponse(responseCode = "400", description = "Falló el registro",
                    content = @Content(schema = @Schema(implementation = Object.class)))
    })
    @PostMapping(value = "", produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public UserDTO registerUser(@RequestBody UserInfo userInfo) {
        log.info("Iniciando el registro : {}", userInfo.getEmail());
        return userCreateUC.register(userInfo);
    }
}
