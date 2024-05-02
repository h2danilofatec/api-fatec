package br.com.api.fatec.apifatec.domain.cliente.mapper;

import br.com.api.fatec.apifatec.domain.cliente.dtos.ClienteDTO;
import br.com.api.fatec.apifatec.entities.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteMapper {
    public static Cliente toEntity(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setNome(dto.getNome());
        cliente.setRazaoSocial(dto.getRazaoSocial());
        cliente.setEndereco(dto.getEndereco());
        cliente.setEmail(dto.getEmail());
        return cliente;
    }

    public static ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setRazaoSocial(cliente.getRazaoSocial());
        dto.setEndereco(cliente.getEndereco());
        dto.setEmail(cliente.getEmail());
        return dto;
    }

    public static List<ClienteDTO> toDTOList(List<Cliente> clientes) {
        return clientes.stream().map(ClienteMapper::toDTO).collect(Collectors.toList());
    }
}
