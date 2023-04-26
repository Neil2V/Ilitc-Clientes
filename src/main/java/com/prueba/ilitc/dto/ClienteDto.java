package com.prueba.ilitc.dto;

import com.prueba.ilitc.enums.SexoEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import javax.validation.constraints.NotBlank;
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidoPaterno;

    @NotBlank
    private String apellidoMaterno;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;

    @NotBlank
    private String fechaNacimiento;

    @NotBlank
    private String direccion;

    @NotBlank
    private String email;
}
