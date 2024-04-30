package br.com.api.fatec.apifatec.domain.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.fatec.apifatec.entities.Produto;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> listarProdutos() {
		return produtoRepository.findAll();
	}

	public Produto encontrarProdutoPorId(Long id) {
		return produtoRepository.findById(id).orElse(null);
	}

	public Produto salvarProduto(Produto Produto) {
		return produtoRepository.save(Produto);
	}

	public void deletarProduto(Long id) {
		Produto Produto = encontrarProdutoPorId(id);
		
		if(Produto == null)
			throw new IllegalArgumentException("Produto nao existe");
		
		
		produtoRepository.deleteById(id);
	}
	
	public Produto atualizarProduto(Long id, Produto produto) {
		Produto ProdutoCadastrado = encontrarProdutoPorId(id);
		
		if (ProdutoCadastrado == null)
		{
			return null;
		} else {
			ProdutoCadastrado.setDescricao(produto.getDescricao());
			ProdutoCadastrado.setPreco(produto.getPreco());
			ProdutoCadastrado.setQuantidadeEstoque(produto.getQuantidadeEstoque());
			ProdutoCadastrado.setAtivo(produto.getAtivo());
			return produtoRepository.save(ProdutoCadastrado);
		}
	}
}
