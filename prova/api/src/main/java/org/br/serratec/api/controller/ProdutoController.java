package org.br.serratec.api.controller;

import java.util.List;

import org.br.serratec.api.dtos.MenorMaiorDTO;
import org.br.serratec.api.dtos.ProdutoDTO;
import org.br.serratec.api.entity.Tipo;
import org.br.serratec.api.services.ProdutoServices;
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
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoServices services;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> buscarTodos() {
        return ResponseEntity.ok(services.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.of(services.buscarPorId(id));
    }

    @GetMapping("/nome")
    public ResponseEntity<List<ProdutoDTO>> buscarPorNome(@RequestBody String nome){
        return ResponseEntity.ok(services.buscarPorNome(nome));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ProdutoDTO>> buscarPorTipo(@PathVariable String tipo){
        return ResponseEntity.ok(services.buscarPorTipo(Tipo.valueOf(tipo.toUpperCase())));
    }

    @GetMapping("/preco")
    public ResponseEntity<List<ProdutoDTO>> buscarPorPreco(@RequestBody MenorMaiorDTO dto){
        return ResponseEntity.ok(services.buscarPorPreco(dto.menor(), dto.maior()));
    }

    @GetMapping("/preco/{preco}")
    public ResponseEntity<List<ProdutoDTO>> buscarPorPrecoMenorQue(@PathVariable Double preco){
        return ResponseEntity.ok(services.buscarPorPrecoMenorQue(preco));
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> adicionar(@Valid @RequestBody ProdutoDTO produto) {
        return ResponseEntity.ok(services.adicionar(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(@Valid @PathVariable Long id, @RequestBody ProdutoDTO dto) {
        return ResponseEntity.of(services.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return ResponseEntity.of(services.deletar(id));
    }
}
