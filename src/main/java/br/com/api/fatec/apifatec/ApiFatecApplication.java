package br.com.api.fatec.apifatec;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.api.fatec.apifatec.domain.pedidovenda.PedidoVendaService;
import br.com.api.fatec.apifatec.entities.PedidoVenda;
import br.com.api.fatec.apifatec.entities.PedidoVendaItem;
import br.com.api.fatec.apifatec.shared.enums.PedidoVendaStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.api.fatec.apifatec.domain.cliente.ClienteRepository;
import br.com.api.fatec.apifatec.domain.pedidovenda.PedidoVendaRepository;
import br.com.api.fatec.apifatec.domain.produto.ProdutoRepository;
import br.com.api.fatec.apifatec.entities.Cliente;

import br.com.api.fatec.apifatec.entities.Produto;

@SpringBootApplication
public class ApiFatecApplication {
	@Bean
	public CommandLineRunner run(@Autowired ClienteRepository clienteRepository,
								 @Autowired ProdutoRepository produtoRepository,
								 @Autowired PedidoVendaRepository pedidoVendaRepository
								 ) {
		return args -> {
			Cliente cliente = new Cliente();
			cliente.setNome("Danilo");
			cliente.setEmail("h2danilofatec@hotmail.com");
			cliente.setEndereco("Rua xxx, 126");
			cliente.setRazaoSocial("Danilo");
			
			clienteRepository.save(cliente);
			
			Cliente cliente2 = new Cliente();
			cliente2.setNome("Arthur");
			cliente2.setEmail("h2danilosfatec@hotmail.com");
			cliente2.setEndereco("Rua xxx, 126");
			cliente2.setRazaoSocial("Arthur");
			
			clienteRepository.save(cliente2);
			
			Produto p1 = new Produto();
			p1.setDescricao("teste");
			p1.setPreco(new BigDecimal(100));
			p1.setAtivo("SIM");
			p1.setQuantidadeEstoque(null);
			produtoRepository.save(p1);
			
			PedidoVenda ped = new PedidoVenda();
			ped.setCliente(cliente2);
			ped.setStatus(PedidoVendaStatusEnum.CONCLUIDO);
			LocalDate data = LocalDate.now();
			ped.setEmissao(data);

			//Instanciando PedidoVendaItem na variavel item1
			PedidoVendaItem item1 = new PedidoVendaItem();

			//Inserindo valores nas propriedades de PedidoVendaItem
			item1.setProduto(p1);
			item1.setQuantidade(10);
			item1.setValorUnitario(new BigDecimal(10));
			item1.setValorTotal(item1.getValorUnitario().multiply(BigDecimal.valueOf(item1.getQuantidade())));

			//Adicionando Item no Pedido Venda
			ped.addItem(item1);

			//Instanciando PedidoVendaItem na variavel item2
			PedidoVendaItem item2 = new PedidoVendaItem();

			//Inserindo valores nas propriedades de PedidoVendaItem
			item2.setProduto(p1);
			item2.setQuantidade(10);
			item2.setValorUnitario(new BigDecimal(10));
			item2.setValorTotal(item2.getValorUnitario().multiply(BigDecimal.valueOf(item2.getQuantidade())));

			//Adicionando Item no Pedido Venda
			ped.addItem(item2);

			//Calculando total dos itens do Pedido Venda
			ped.setTotal();

			//Salvando Pedido Venda
			pedidoVendaRepository.save(ped);
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ApiFatecApplication.class, args);
	}
}
