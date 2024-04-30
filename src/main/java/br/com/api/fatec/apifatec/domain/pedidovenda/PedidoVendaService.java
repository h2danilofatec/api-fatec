package br.com.api.fatec.apifatec.domain.pedidovenda;

import br.com.api.fatec.apifatec.domain.cliente.ClienteService;
import br.com.api.fatec.apifatec.domain.produto.ProdutoService;
import br.com.api.fatec.apifatec.entities.Cliente;
import br.com.api.fatec.apifatec.entities.PedidoVenda;
import br.com.api.fatec.apifatec.entities.PedidoVendaItem;
import br.com.api.fatec.apifatec.entities.Produto;
import br.com.api.fatec.apifatec.shared.enums.PedidoVendaStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoVendaService {
    @Autowired
    private PedidoVendaRepository pedidoVendaRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @Transactional
    public PedidoVenda salvarPedidoVenda(PedidoVenda pedidoVenda) {
        //validando se existe itens no pedido venda
        if (pedidoVenda.getItems().isEmpty())
            throw new IllegalStateException("Favor informar itens do Pedido.");

        //Validando se foi informado Cliente
        if (pedidoVenda.getCliente() == null) {
            throw new IllegalStateException("Não informado Cliente no Pedido Venda.");
        }

        //Carregando Cliente
        Cliente clientePedidoVenda = clienteService.encontrarClientePorId(pedidoVenda.getCliente().getId());
        if (clientePedidoVenda == null) {
            throw new IllegalStateException("Cliente "+pedidoVenda.getCliente().getId()+" informado no Pedido não foi localizado.");
        }

        //Instanciando Classe Pedido Venda
        PedidoVenda pedidoCriar = new PedidoVenda();
        pedidoCriar.setCliente(clientePedidoVenda);
        LocalDate data = LocalDate.now();
        pedidoCriar.setEmissao(data);
        pedidoCriar.setStatus(PedidoVendaStatusEnum.CONCLUIDO);

        //Realizando loop nos itens do pedido venda enviado
        List<PedidoVendaItem> itens = pedidoVenda.getItems(); // assumindo que getItems() retorna uma List
        for (int i = 0; i < itens.size(); i++) {
            PedidoVendaItem item = itens.get(i);

            // Validando se foi informado Produto
            if (item.getProduto() == null) {
                throw new IllegalStateException("Não informado Produto no item " + (i + 1) + ".");
            }

            //Carregando Produto
            Produto produto = produtoService.encontrarProdutoPorId(item.getProduto().getId());
            if (produto == null) {
                throw new IllegalStateException("Não localizado Produto com Cód."+item.getProduto().getId()+" informado no item " + (i + 1) + ".");
            }

            //validando se produto do item esta ativo
            if (!"SIM".equals(produto.getAtivo())) {
                throw new IllegalStateException("Produto informado no item " + (i + 1) + " não está ativo.");
            }

            //carregando dados para criar item do pedido venda
            PedidoVendaItem pedidoVendaItem = new PedidoVendaItem();
            pedidoVendaItem.setProduto(produto);
            pedidoVendaItem.setQuantidade(item.getQuantidade());
            pedidoVendaItem.setValorUnitario(produto.getPreco()); //Pode decidir como será a regra do negócio. Ex: Se irá considerar valor unitario enviado na requisição ou se irá considerar o preço definido no cadastro Produto
            pedidoVendaItem.setValorTotalItem();

            //Adicionando item no Pedido Venda para criar
            pedidoCriar.addItem(pedidoVendaItem);
        }

        pedidoCriar.setTotal(); //calculando total dos itens do pedido e salvando total do pedido venda
        return pedidoVendaRepository.save(pedidoCriar); //criando pedido venda no banco dados e retornando pedido criado
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
                item.setValorTotalItem();
                pedidoExistente.addItem(item);
            });

            // Recalcula o total após a atualização dos itens
            pedidoExistente.setTotal();

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
