package br.com.ecommerce.service;

import br.com.ecommerce.exception.EstoqueInsuficienteException;
import br.com.ecommerce.model.Produto;

import java.util.HashMap;
import java.util.Map;

public class CarrinhoService {
    private Map<Produto, Integer> itens = new HashMap<>();
    private Map<String, Double> cuponsValidos = new HashMap<>();
    private double descontoAtual = 0.0;

    public CarrinhoService() {
        cuponsValidos.put("DEV10", 0.10);
        cuponsValidos.put("DEV20", 0.20);
    }

    public void adicionarItem (Produto produto, int quantidade, EstoqueService estoque) {
        if (!estoque.temEstoqueDisponivel(produto.getId(), quantidade)) {
            throw new EstoqueInsuficienteException("Estoque insuficiente para o produto " + produto.getNome());
        }
        int qtdAtualNoCarrinho = itens.getOrDefault(produto,0);
        itens.put(produto, qtdAtualNoCarrinho + quantidade);

        System.out.println(quantidade + "x " + produto.getNome() + " adicionado ao carrinho com sucesso");
    }

}
