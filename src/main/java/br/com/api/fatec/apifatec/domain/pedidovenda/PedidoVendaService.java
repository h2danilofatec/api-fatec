package br.com.api.fatec.apifatec.domain.pedidovenda;

import br.com.api.fatec.apifatec.entities.PedidoVenda;
import br.com.api.fatec.apifatec.entities.PedidoVendaItem;
import br.com.api.fatec.apifatec.shared.enums.PedidoVendaStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoVendaService {
    @Autowired
    private PedidoVendaRepository pedidoVendaRepository;
    @Transactional
    public PedidoVenda salvarPedidoVenda(PedidoVenda pedidoVenda) {
        if (pedidoVenda.getItems().isEmpty())
            throw new IllegalStateException("Favor informar itens do Pedido.");

        PedidoVenda pedidoCriar = new PedidoVenda();

        pedidoVenda.getItems().forEach(item -> {
            item.setValorUnitario(item.getProduto().getPreco());
            item.setValorTotal(item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())));
            pedidoVenda.addItem(item);
        });

        return pedidoVendaRepository.save(pedidoVenda);
    }

    public Optional<PedidoVenda> buscarPorId(Long id) {
        return pedidoVendaRepository.findById(id);
    }

    public List<PedidoVenda> listarTodos() {
        return pedidoVendaRepository.findAll();
    }

    @Transactional
    public void atualizarStatus(Long id, PedidoVendaStatusEnum novoStatus) {
        pedidoVendaRepository.findById(id).ifPresent(pedido -> {
            pedido.setStatus(novoStatus);
            pedidoVendaRepository.save(pedido);
        });
    }

    @Transactional
    public PedidoVenda alterarPedido(Long id, PedidoVenda dadosNovos) {
        return pedidoVendaRepository.findById(id).map(pedidoExistente -> {
            // Atualiza os campos necessários
            if (dadosNovos.getCliente() != null) {
                pedidoExistente.setCliente(dadosNovos.getCliente());
            }
            if (dadosNovos.getEmissao() != null) {
                pedidoExistente.setEmissao(dadosNovos.getEmissao());
            }

            // Atualiza os itens do pedido
            pedidoExistente.getItems().clear();  // Limpa os itens existentes
            // Re-adiciona os itens com atualizações
            dadosNovos.getItems().forEach(item -> {
                item.setValorTotal(item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())));
                pedidoExistente.addItem(item);
            });

            // Recalcula o total após a atualização dos itens
            pedidoExistente.setTotal(pedidoExistente.getItems().stream()
                    .map(PedidoVendaItem::getValorTotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add));

            return pedidoVendaRepository.save(pedidoExistente);
        }).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    @Transactional
    public PedidoVenda cancelarPedido(Long id) {
        return pedidoVendaRepository.findById(id).map(pedido -> {
            // Verifica se o pedido pode ser cancelado
            if (pedido.getStatus().equals(PedidoVendaStatusEnum.CONCLUIDO)) {
                pedido.setStatus(PedidoVendaStatusEnum.CANCELADO);
                return pedidoVendaRepository.save(pedido);
            } else {
                throw new IllegalStateException("O pedido já se encontra Cancelado.");
            }
        }).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }
}
