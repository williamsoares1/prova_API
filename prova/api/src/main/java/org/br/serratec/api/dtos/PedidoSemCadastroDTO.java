package org.br.serratec.api.dtos;

import java.time.LocalDate;
import java.util.List;

public record PedidoSemCadastroDTO(
        Long id,
        LocalDate data_expedicao,
        Long cliente,
        List<Long> produtos) {

}
