package br.com.ecommerce.service;

import br.com.ecommerce.exception.ProdutoNaoEncontradoException;
import br.com.ecommerce.model.Produto;

import java.util.HashMap;
import java.util.Map;

public class EstoqueService {
    private Map<Long, Produto> catalogo = new HashMap<>();
    private Map<Long, Integer> quantidades = new HashMap<>();

    public void cadastrarProduto(Produto produto, int quantidadeInicial) {
        catalogo.put(produto.getId(), produto);
        quantidades.put(produto.getId(), quantidadeInicial);
        System.out.println("Produto Cadastrado com sucesso no estoque!");
    }

    public Produto buscarProduto(long id) {
        Produto p = catalogo.get(id);

        if(p == null) {
            throw new ProdutoNaoEncontradoException("Produto com ID " + id + " nao encontrado!");
        }
        return p;
    }
}
