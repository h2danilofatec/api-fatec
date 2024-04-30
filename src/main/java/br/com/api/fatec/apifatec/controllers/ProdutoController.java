package br.com.api.fatec.apifatec.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.fatec.apifatec.domain.produto.ProdutoService;
import br.com.api.fatec.apifatec.entities.Produto;

@RestController()
@RequestMapping("/api/produtos/v1")
public class ProdutoController {
	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> listarProdutos() {
		List<Produto> produtos = produtoService.listarProdutos();
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> encontrarProdutoPorId(@PathVariable Long id) {
		Produto produto = produtoService.encontrarProdutoPorId(id);
		return produto != null ? new ResponseEntity<>(produto, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping()
	public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto) {
		Produto produtoSalvo = produtoService.salvarProduto(produto);
		return new ResponseEntity<>(produtoSalvo, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
		produtoService.deletarProduto(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> AtualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
		Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);
		return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
	}
}
