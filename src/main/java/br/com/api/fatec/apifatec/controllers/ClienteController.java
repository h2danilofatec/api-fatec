package br.com.api.fatec.apifatec.controllers;

import java.util.List;

import br.com.api.fatec.apifatec.domain.cliente.ClienteService;
import br.com.api.fatec.apifatec.domain.cliente.ClienteMapper;
import br.com.api.fatec.apifatec.domain.cliente.ClienteDTO;
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
@RequestMapping("/api/clientes/v1")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listarClientes() {
		List<ClienteDTO> clientes = ClienteMapper.toDTOList(clienteService.listarClientes());
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> encontrarClientePorId(@PathVariable Long id) {
		ClienteDTO cliente = ClienteMapper.toDTO(clienteService.encontrarClientePorId(id));
		return cliente != null ? new ResponseEntity<>(cliente, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> salvarCliente(@RequestBody ClienteDTO clienteDTO) {
		Cliente cliente = ClienteMapper.toEntity(clienteDTO);
		ClienteDTO clienteSalvo = ClienteMapper.toDTO(clienteService.salvarCliente(cliente));
		return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
		clienteService.deletarCliente(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
