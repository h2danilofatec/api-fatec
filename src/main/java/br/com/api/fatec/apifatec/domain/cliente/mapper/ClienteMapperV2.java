package br.com.api.fatec.apifatec.domain.cliente.mapper;

import br.com.api.fatec.apifatec.domain.cliente.dtos.ClienteDTO;
import br.com.api.fatec.apifatec.entities.Cliente;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

//V2 - Iremos utilizar biblioteca ModelMapper
public class ClienteMapperV2 {
    public static Cliente toEntity(ClienteDTO dto) {
        Cliente clienteEntity = new ModelMapper().map(dto, Cliente.class);
        return  clienteEntity;
    }

    public static ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ModelMapper().map(cliente, ClienteDTO.class);
        return dto;
    }

    public static List<ClienteDTO> toDTOList(List<Cliente> clientes) {
        return clientes.stream().map(ClienteMapperV2::toDTO).collect(Collectors.toList());
    }
}
