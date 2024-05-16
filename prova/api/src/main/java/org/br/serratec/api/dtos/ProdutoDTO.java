package org.br.serratec.api.dtos;

import java.math.BigDecimal;

import org.br.serratec.api.entity.Produto;
import org.br.serratec.api.entity.Tipo;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProdutoDTO(Long id,
        @NotBlank @Size(max = 40) String nome,
        @Size(max = 120) String descricao,
        @NotNull @Enumerated(EnumType.STRING) Tipo tipo,
        @NotNull BigDecimal preco) {

    public Produto toEntity() {
        return new Produto(this.id, this.nome, this.descricao, this.tipo, this.preco);
    }
}
