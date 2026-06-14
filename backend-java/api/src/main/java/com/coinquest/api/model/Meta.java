package com.coinquest.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "metas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título da meta é obrigatório.")
    private String titulo;

    @NotNull(message = "O valor alvo é obrigatório.")
    @Positive(message = "O valor alvo deve ser maior que zero.")
    private BigDecimal valorAlvo;

    private BigDecimal valorPoupado = BigDecimal.ZERO; // Vai começar zerado por padrão

    private LocalDate dataLimite;

    // Relacionamento: Muitas metas pertecem a um só usuário
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
