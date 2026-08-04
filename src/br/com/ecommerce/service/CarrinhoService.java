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
    public double calcularSubtotal(){
        double subtotal = 0.0;

        for(Map.Entry<Produto, Integer> entry : itens.entrySet()) {
            Produto produto = entry.getKey();
            int quantidade = entry.getValue();

            subtotal += produto.getPreco() * quantidade;
        }
        return subtotal;
    }

    public void aplicarCupom(String codigo) {
        if (cuponsValidos.containsKey(codigo)) {

            this.descontoAtual = cuponsValidos.get(codigo);
            System.out.println("Cupom  '" + codigo + "' aplicado com sucesso");
        } else {
            System.out.println("Cupom invalido");
        }
    }

    public Double calcularTotalFinal(){
        double subtotal = calcularSubtotal();
        double valorDesconto = subtotal * descontoAtual;
        return subtotal - valorDesconto;
    }
    public void finalizarCompra(EstoqueService estoque) {
        if (itens.isEmpty()){
            System.out.println("Seu carrinho está vazio! Adicione produtos antes de finalizar.");
            return;
        }
        System.out.println("Finalizando compra. . .");
        System.out.println("Total a pagar: R$ " + String.format("%.2f", calcularTotalFinal()));

        for(Map.Entry<Produto, Integer> entry : itens.entrySet()) {
            Produto produto = entry.getKey();
            int quantidadeComprada = entry.getValue();

            estoque.darBaixaEstoque(produto.getId(), quantidadeComprada);
        }
        itens.clear();
        this.descontoAtual = 0.0;
        System.out.println("Compra finalizado com Sucesso!");
    }
}
