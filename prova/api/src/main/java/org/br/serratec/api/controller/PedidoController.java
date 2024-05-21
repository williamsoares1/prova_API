package org.br.serratec.api.controller;

import java.util.List;

import org.br.serratec.api.dtos.PedidoDTO;
import org.br.serratec.api.dtos.PedidoSemCadastroDTO;
import org.br.serratec.api.services.PedidoServices;
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
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoServices services;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> buscarTodos() {
        return ResponseEntity.ok(services.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.of(services.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> adicionar(@Valid @RequestBody PedidoSemCadastroDTO pedido) {
        return ResponseEntity.ok(services.adicionar(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> atualizar(@Valid @PathVariable Long id, @RequestBody PedidoDTO dto) {
        return ResponseEntity.of(services.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return ResponseEntity.of(services.deletar(id));
    }

}
