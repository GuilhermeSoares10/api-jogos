package com.francisco.api_jogos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O tipo é obrigatório")
    private String tipo;

    @Min(value = 0, message = "A nota deve ser no mínimo 0")
    @Max(value = 10, message = "A nota deve ser no máximo 10")
    private Integer nota;

    @NotBlank(message = "A review é obrigatória")
    private String review;
}