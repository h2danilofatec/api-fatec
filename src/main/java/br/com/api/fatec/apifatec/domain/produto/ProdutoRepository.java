package br.com.api.fatec.apifatec.domain.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.fatec.apifatec.entities.Produto;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}