package org.br.serratec.api.repository;

import java.util.List;

import org.br.serratec.api.entity.Produto;
import org.br.serratec.api.entity.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    List<Produto> findByNomeContaining(String nome);
    List<Produto> findByTipo(Tipo tipo);
    List<Produto> findByPrecoBetween(BigDecimal menor, BigDecimal maior);
    List<Produto> findByPrecoLessThan(BigDecimal preco);
}
