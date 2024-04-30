package br.com.api.fatec.apifatec.domain.pedidovenda;

import br.com.api.fatec.apifatec.entities.PedidoVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PedidoVendaRepository extends JpaRepository<PedidoVenda, Long>, JpaSpecificationExecutor<PedidoVenda> {

}
