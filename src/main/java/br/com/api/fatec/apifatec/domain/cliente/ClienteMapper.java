package br.com.api.fatec.apifatec.domain.cliente.dao;

import br.com.api.fatec.apifatec.domain.cliente.dto.ClienteDTO;
import br.com.api.fatec.apifatec.entities.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteDAO {
    public static Cliente toEntity(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setNome(dto.getNome());
        cliente.setSobrenome(dto.getSobrenome());
        cliente.setEndereco(dto.getEndereco());
        cliente.setEmail(dto.getEmail());
        return cliente;
    }

    public static ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setSobrenome(cliente.getSobrenome());
        dto.setEndereco(cliente.getEndereco());
        dto.setEmail(cliente.getEmail());
        return dto;
    }

    public static List<ClienteDTO> toDTOList(List<Cliente> clientes) {
        return clientes.stream().map(ClienteDAO::toDTO).collect(Collectors.toList());
    }
}
