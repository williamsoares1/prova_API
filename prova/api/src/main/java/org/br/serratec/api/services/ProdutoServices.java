package org.br.serratec.api.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.br.serratec.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.br.serratec.api.dtos.ProdutoDTO;
import org.br.serratec.api.entity.Produto;
import org.br.serratec.api.entity.Tipo;

@Service
public class ProdutoServices {

    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoDTO> buscarTodos() {
        return repository.findAll().stream().map(p -> new ProdutoDTO(p.getId(), p.getNome(), p.getDescricao(), p.getTipo(), p.getPreco())).collect(Collectors.toList());
    }

    public Optional<ProdutoDTO> buscarPorId(Long id){
        Optional<Produto> entity = repository.findById(id);

        if(entity.isPresent()){
            return Optional.of(entity.get().toDTO());
        }

        return Optional.empty();
    }

    public List<ProdutoDTO> buscarPorNome(String nome) {
        return repository.findByNomeContaining(nome).stream().map(p -> new ProdutoDTO(p.getId(), p.getNome(), p.getDescricao(), p.getTipo(), p.getPreco())).collect(Collectors.toList());
    }

    public List<ProdutoDTO> buscarPorPreco(Double menor, Double maior) {
        return repository.findByPrecoBetween(BigDecimal.valueOf(menor), BigDecimal.valueOf(maior)).stream().map(p -> new ProdutoDTO(p.getId(), p.getNome(), p.getDescricao(), p.getTipo(), p.getPreco())).collect(Collectors.toList());
    }

    public List<ProdutoDTO> buscarPorTipo(Tipo tipo){
        return repository.findByTipo(tipo).stream().map(p -> new ProdutoDTO(p.getId(), p.getNome(), p.getDescricao(), p.getTipo(), p.getPreco())).collect(Collectors.toList());
    }

    public List<ProdutoDTO> buscarPorPrecoMenorQue(Double preco){
        return repository.findByPrecoLessThan(BigDecimal.valueOf(preco)).stream().map(p -> new ProdutoDTO(p.getId(), p.getNome(), p.getDescricao(), p.getTipo(), p.getPreco())).collect(Collectors.toList());
    }

    public ProdutoDTO adicionar(ProdutoDTO dto) {
        Produto entity = repository.save(dto.toEntity());
        return entity.toDTO();
    }

    public Optional<ProdutoDTO> atualizar(Long id, ProdutoDTO dto){
        Produto entity = dto.toEntity();

        if(repository.existsById(id)){
            entity.setId(id);
            repository.save(entity);
            return Optional.of(entity.toDTO());
        }

        return Optional.empty();
    }

    public Optional<Void> deletar(Long id){
        Optional<Produto> entity = repository.findById(id);

        if(entity.isPresent()){
            repository.deleteById(id);;
        }

        return Optional.empty();
    }
}
