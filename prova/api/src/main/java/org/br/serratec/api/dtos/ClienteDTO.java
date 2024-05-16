package org.br.serratec.api.dtos;

import java.time.LocalDate;

import org.br.serratec.api.entity.Cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteDTO(Long id,
    @NotBlank @Size(max = 80) String nome,
    @NotBlank @Pattern(regexp = "\\d{11}") String cpf,
    @NotNull LocalDate dataNascimento) {

    public Cliente toEntity(){
        return new Cliente(this.id, this.nome, this.cpf, this.dataNascimento);
    }
}
