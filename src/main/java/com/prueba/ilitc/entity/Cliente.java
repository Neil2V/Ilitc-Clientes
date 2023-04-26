package com.prueba.ilitc.entity;

import com.prueba.ilitc.enums.SexoEnum;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
