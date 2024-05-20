package org.br.serratec.api.dtos;

import java.time.LocalDate;
import java.util.List;

import org.br.serratec.api.entity.Cliente;
import org.br.serratec.api.entity.Pedido;
import org.br.serratec.api.entity.Produto;

public record PedidoDTO(Long id,
        LocalDate data_expedicao,
        Cliente cliente,
        List<Produto> produtos) {

    public Pedido toEntity() {
        return new Pedido(this.id, this.data_expedicao, this.cliente, this.produtos);
    }
}
