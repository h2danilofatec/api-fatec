package br.com.api.fatec.apifatec.domain.pedidovenda.dtos;

import br.com.api.fatec.apifatec.shared.enums.PedidoVendaStatusEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoVendaDto {
    private Long id;
    private Long clienteId;
    private LocalDate emissao;
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private PedidoVendaStatusEnum status;
    private List<PedidoVendaItemDto> items = new ArrayList<>();
}
