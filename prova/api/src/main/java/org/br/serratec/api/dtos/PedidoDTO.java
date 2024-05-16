package org.br.serratec.api.dtos;

import java.time.LocalDate;

import org.br.serratec.api.entity.Pedido;

public record PedidoDTO(Long id,
        LocalDate data_expedicao,
        Long id_cliente,
        Long id_produto) {

    public Pedido toEntity() {
        return new Pedido(this.id, this.data_expedicao, this.id_cliente, this.id_produto);
    }
}
