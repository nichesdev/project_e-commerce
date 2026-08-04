package br.com.ecommerce.service;

import br.com.ecommerce.model.Produto;

import java.util.HashMap;
import java.util.Map;

public class CarrinhoService {
    private Map<Produto, Integer> itens = new HashMap<>();
    private Map<Produto, Double> cuponsValidos = new HashMap<>();
    private double descontoAtual = 0.0;

    public void adicionarItem (Produto produto, int quantidade, EstoqueService estoque) {
        boolean b = !estoque.temEstoqueDisponivel(produto.getId(), quantidade);
    }
}
