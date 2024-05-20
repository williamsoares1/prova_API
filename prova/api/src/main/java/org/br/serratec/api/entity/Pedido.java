package org.br.serratec.api.entity;

import java.time.LocalDate;
import java.util.List;

import org.br.serratec.api.dtos.PedidoDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long id;
    private LocalDate dataExpedicao;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "produto_pedido", joinColumns = @JoinColumn(name = "id_pedido"), inverseJoinColumns = @JoinColumn(name = "id_produto"))
    private List<Produto> produtos;

    public Pedido() {
    }

    public Pedido(Long id, LocalDate dataExpedicao, Cliente cliente, List<Produto> produtos) {
        super();
        this.id = id;
        this.dataExpedicao = dataExpedicao;
        this.cliente = cliente;
        this.produtos = produtos;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDataExpedicao() {
        return dataExpedicao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDataExpedicao(LocalDate dataExpedicao) {
        this.dataExpedicao = dataExpedicao;
    }

    public void setProduto(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public PedidoDTO toDTO() {
        return new PedidoDTO(this.id, this.dataExpedicao, this.cliente, this.produtos);
    }
}
