package org.br.serratec.api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.br.serratec.api.dtos.PedidoDTO;
import org.br.serratec.api.entity.Pedido;
import org.br.serratec.api.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServices {

    @Autowired
    private PedidoRepository repository;

    public List<PedidoDTO> buscarTodos() {
        return repository.findAll().stream()
                .map(p -> new PedidoDTO(p.getId(), p.getDataExpedicao(), p.getCliente(), p.getProdutos()))
                .collect(Collectors.toList());
    }

    public Optional<PedidoDTO> buscarPorId(Long id) {
        Optional<Pedido> entity = repository.findById(id);

        if (entity.isPresent()) {
            return Optional.of(entity.get().toDTO());
        }

        return Optional.empty();
    }

    public PedidoDTO adicionar(PedidoDTO dto) {
        Pedido entity = repository.save(dto.toEntity());
        return entity.toDTO();
    }

    public Optional<PedidoDTO> atualizar(Long id, PedidoDTO dto) {
        Pedido entity = dto.toEntity();

        if (repository.existsById(id)) {
            entity.setId(id);
            repository.save(entity);
            return Optional.of(entity.toDTO());
        }

        return Optional.empty();
    }

    public Optional<Void> deletar(Long id) {
        Optional<Pedido> entity = repository.findById(id);

        if (entity.isPresent()) {
            repository.deleteById(id);
            ;
        }

        return Optional.empty();
    }

}
