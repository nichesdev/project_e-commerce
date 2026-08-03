package br.com.ecommerce.model;

import java.util.Objects;

public class Produto {
    private long id;
    private String nome;
    private double preco;

    public Produto(long id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }
    // Getters
    public long getId() {return id;}
    public String getNome() {return nome;}
    public double getPreco() {return preco;}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id == produto.id;
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Produto: " + nome + " | Preço: R$ " + String.format("%.2f", preco);
    }
}
