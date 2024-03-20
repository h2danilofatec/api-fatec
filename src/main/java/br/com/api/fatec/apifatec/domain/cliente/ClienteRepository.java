package br.com.api.fatec.apifatec.domain.cliente;

import br.com.api.fatec.apifatec.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
