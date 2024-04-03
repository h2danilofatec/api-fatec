package br.com.api.fatec.apifatec.domain.cliente;

import br.com.api.fatec.apifatec.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> listarClientes() {
		return clienteRepository.findAll();
	}

	public Cliente encontrarClientePorId(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}

	public Cliente salvarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public void deletarCliente(Long id) {
		clienteRepository.deleteById(id);
	}
	
	public Cliente atualizarCliente(Long id, Cliente cliente) {
		Cliente clienteCadastradoCliente = encontrarClientePorId(id);
		
		if (clienteCadastradoCliente == null)
		{
			return null;
		} else {
			clienteCadastradoCliente.setNome(cliente.getNome());
			clienteCadastradoCliente.setRazaoSocial(cliente.getRazaoSocial());
			clienteCadastradoCliente.setEmail(cliente.getEmail());
			clienteCadastradoCliente.setEndereco(cliente.getEndereco());
			return clienteRepository.save(clienteCadastradoCliente);
		}
	}
}
