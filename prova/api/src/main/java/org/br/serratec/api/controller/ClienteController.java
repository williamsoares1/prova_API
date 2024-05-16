package org.br.serratec.api.controller;

import java.util.List;

import org.br.serratec.api.dtos.ClienteDTO;
import org.br.serratec.api.services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteServices services;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> buscarTodos(){
        return ResponseEntity.ok(services.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.of(services.buscarPorId(id));
    }

    @GetMapping("/nasc")
    public ResponseEntity<List<ClienteDTO>> buscarPorDataNasc(@RequestBody String dataNasc){
        return ResponseEntity.ok(services.buscarPorDataNasc(dataNasc));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> adicionar(@Valid @RequestBody ClienteDTO cliente){
        return ResponseEntity.ok(services.adicionar(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@Valid @PathVariable Long id, @RequestBody ClienteDTO dto){
        return ResponseEntity.of(services.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        return ResponseEntity.of(services.deletar(id));
    }
}
