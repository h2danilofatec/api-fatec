package br.com.api.fatec.apifatec.controllers;

import java.util.List;

import br.com.api.fatec.apifatec.domain.cliente.ClienteService;
import br.com.api.fatec.apifatec.domain.cliente.dtos.ClienteDTO;
import br.com.api.fatec.apifatec.domain.cliente.mapper.ClienteMapperV2;
import br.com.api.fatec.apifatec.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/clientes/v2")
public class ClienteControllerV2 {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listarClientes() {
		List<ClienteDTO> clientes = ClienteMapperV2.toDTOList(clienteService.listarClientes());
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> encontrarClientePorId(@PathVariable Long id) {
		ClienteDTO cliente = ClienteMapperV2.toDTO(clienteService.encontrarClientePorId(id));
		return cliente != null ? new ResponseEntity<>(cliente, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> salvarCliente(@RequestBody ClienteDTO clienteDTO) {
		Cliente cliente = ClienteMapperV2.toEntity(clienteDTO);
		ClienteDTO clienteSalvo = ClienteMapperV2.toDTO(clienteService.salvarCliente(cliente));
		return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
		clienteService.deletarCliente(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
