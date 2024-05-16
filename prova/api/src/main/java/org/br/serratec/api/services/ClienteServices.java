package org.br.serratec.api.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.br.serratec.api.dtos.ClienteDTO;
import org.br.serratec.api.entity.Cliente;
import org.br.serratec.api.repository.ClienteRepository;

@Service
public class ClienteServices {

    @Autowired
    private ClienteRepository repository;

    public List<ClienteDTO> buscarTodos() {
        return repository.findAll().stream().map(c -> new ClienteDTO(c.getId(), c.getNome(), c.getCpf(), c.getDataNascimento())).collect(Collectors.toList());
    }

    public Optional<ClienteDTO> buscarPorId(Long id){
        Optional<Cliente> entity = repository.findById(id);

        if(entity.isPresent()){
            return Optional.of(entity.get().toDTO());
        }

        return Optional.empty();
    }

    public List<ClienteDTO> buscarPorNome(String nome) {
        return repository.findByNomeContaining(nome).stream().map(c -> new ClienteDTO(c.getId(), c.getNome(), c.getCpf(), c.getDataNascimento())).collect(Collectors.toList());
    }

    public List<ClienteDTO> buscarPorDataNasc(String dataNasc) {
        return repository.findByDataNascimentoAfter(LocalDate.parse(dataNasc)).stream().map(c -> new ClienteDTO(c.getId(), c.getNome(), c.getCpf(), c.getDataNascimento())).collect(Collectors.toList());
    }

    public ClienteDTO adicionar(ClienteDTO dto) {
        Cliente entity = repository.save(dto.toEntity());
        return entity.toDTO();
    }

    public Optional<ClienteDTO> atualizar(Long id, ClienteDTO dto){
        Cliente entity = dto.toEntity();

        if(repository.existsById(id)){
            entity.setId(id);
            repository.save(entity);
            return Optional.of(entity.toDTO());
        }

        return Optional.empty();
    }

    public Optional<Void> deletar(Long id){
        Optional<Cliente> entity = repository.findById(id);

        if(entity.isPresent()){
            repository.deleteById(id);;
        }

        return Optional.empty();
    }

}
