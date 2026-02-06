package com.gaei.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequestDTO {

    @NotBlank
    private String idTx;

    @NotBlank(message = "El tipo de documento es obligatorio")
    @Pattern(
            regexp = "CC|CE|PA",
            message = "Tipo de documento inválido"
    )
    private String tipoDocumento;

    @NotBlank(message = "El número de documento es obligatorio")
    @Pattern(
            regexp = "\\d{6,15}",
            message = "Número de documento inválido"
    )
    private String numeroDocumento;

    @NotBlank(message = "El primer nombre es obligatorio")
    @Size(max = 50, message = "Máximo 50 caracteres")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$",
            message = "El nombre contiene caracteres inválidos"
    )
    private String primerNombre;

    @Size(max = 50, message = "Máximo 50 caracteres")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$",
            message = "El segundo nombre contiene caracteres inválidos"
    )
    private String segundoNombre;

    @NotBlank(message = "El primer apellido es obligatorio")
    @Size(max = 50, message = "Máximo 50 caracteres")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$",
            message = "El apellido contiene caracteres inválidos"
    )
    private String primerApellido;

    @NotBlank(message = "El segundo apellido es obligatorio")
    @Size(max = 50, message = "Máximo 50 caracteres")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$",
            message = "El segundo apellido contiene caracteres inválidos"
    )
    private String segundoApellido;

    @NotNull(message = "El teléfono es obligatorio")
    private Long telefono;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "Formato de correo inválido")
    @Size(max = 100)
    private String correoElectronico;
}