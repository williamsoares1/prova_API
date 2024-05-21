package org.br.serratec.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.br.serratec.api.dtos.PedidoDTO;
import org.br.serratec.api.dtos.PedidoSemCadastroDTO;
import org.br.serratec.api.entity.Cliente;
import org.br.serratec.api.entity.Pedido;
import org.br.serratec.api.entity.Produto;
import org.br.serratec.api.repository.ClienteRepository;
import org.br.serratec.api.repository.PedidoRepository;
import org.br.serratec.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServices {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<PedidoDTO> buscarTodos() {
        return pedidoRepository.findAll().stream()
                .map(p -> new PedidoDTO(p.getId(), p.getDataExpedicao(), p.getCliente(), p.getProdutos()))
                .collect(Collectors.toList());
    }

    public Optional<PedidoDTO> buscarPorId(Long id) {
        Optional<Pedido> entity = pedidoRepository.findById(id);

        if (entity.isPresent()) {
            return Optional.of(entity.get().toDTO());
        }

        return Optional.empty();
    }

    public PedidoDTO adicionar(PedidoSemCadastroDTO dto) {
        Cliente clienteEntity = clienteRepository.findById(dto.cliente()).get();
        List<Produto> produtos = new ArrayList<Produto>();

        for(Long id : dto.produtos()){
            Optional<Produto> produtoEntity = produtoRepository.findById(id);
            if(produtoEntity.isPresent()){
                produtos.add(produtoEntity.get());
            }
            else{
                System.out.println("Produto não encontrado");
            }
        }

        Pedido entity = new Pedido(dto.id(), dto.data_expedicao(), clienteEntity, produtos);
        pedidoRepository.save(entity);

        return entity.toDTO();
    }

    public Optional<PedidoDTO> atualizar(Long id, PedidoDTO dto) {
        Pedido entity = dto.toEntity();

        if (pedidoRepository.existsById(id)) {
            entity.setId(id);
            pedidoRepository.save(entity);
            return Optional.of(entity.toDTO());
        }

        return Optional.empty();
    }

    public Optional<Void> deletar(Long id) {
        Optional<Pedido> entity = pedidoRepository.findById(id);

        if (entity.isPresent()) {
            pedidoRepository.deleteById(id);
            ;
        }

        return Optional.empty();
    }

}
