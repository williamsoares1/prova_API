package org.br.serratec.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.br.serratec.api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    List<Cliente> findByNomeContaining(String nome);
    List<Cliente> findByDataNascimentoAfter(LocalDate dataNascimento);
}
