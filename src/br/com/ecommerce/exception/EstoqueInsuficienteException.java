package br.com.ecommerce.exception;

public class EstoqueInsuficienteException extends RuntimeException {
    public EstoqueInsuficienteException(String msg) {
        super(msg);
    }
}
