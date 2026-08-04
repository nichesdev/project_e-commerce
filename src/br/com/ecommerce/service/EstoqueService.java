package br.com.ecommerce.service;

import br.com.ecommerce.exception.EstoqueInsuficienteException;
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

    public Produto buscarProduto(Long id) {
        Produto p = catalogo.get(id);

        if(p == null) {
            throw new ProdutoNaoEncontradoException("Produto com ID " + id + " não encontrado!");
        }
        return p;
    }
    public boolean temEstoqueDisponivel(Long id, int quantidadeDesejada) {
        int quantidadeAtual = quantidades.getOrDefault(id, 0);
        return quantidadeAtual >= quantidadeDesejada;
    }
    public void darBaixaEstoque(Long id, int quantidadeComprada) {
        if (!temEstoqueDisponivel(id, quantidadeComprada)) {
            throw new EstoqueInsuficienteException("Estoque insuficiente para o produto ID " + id);
        }
        int quantidadeAtual = quantidades.get(id);

        quantidades.put(id, quantidadeAtual - quantidadeComprada);
    }
    public void listarEstoque() {
        if (catalogo.isEmpty()) {
            System.out.println("O Estoque está vazio.");
            return;
        }
        System.out.println("\n--- ESTOQUE ATUAL ---");
        for (Produto p : catalogo.values()) {
            int qtd = quantidades.get(p.getId());
            System.out.println(p + " | Quantidade: " + qtd);
        }
    }
}
