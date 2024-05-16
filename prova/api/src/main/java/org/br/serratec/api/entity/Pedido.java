package org.br.serratec.api.entity;

import java.time.LocalDate;

import org.br.serratec.api.dtos.PedidoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id_pedido") Long id;
    private LocalDate data_expedicao;
    private Long id_cliente;
    private Long id_produto;

    public Pedido(){}

    public Pedido(Long id, LocalDate data_expedicao, Long id_cliente, Long id_produto) {
        super();
        this.id = id;
        this.data_expedicao = data_expedicao;
        this.id_cliente = id_cliente;
        this.id_produto = id_produto;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getData_expedicao() {
        return data_expedicao;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public Long getId_produto() {
        return id_produto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setData_expedicao(LocalDate data_expedicao) {
        this.data_expedicao = data_expedicao;
    }

    public void setId_produto(Long id_produto) {
        this.id_produto = id_produto;
    }

    public PedidoDTO toDTO(){
        return new PedidoDTO(this.id, this.data_expedicao, this.id_cliente, this.id_produto);
    }
}
