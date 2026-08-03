package br.com.ecommerce.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(String msg) {
        super(msg);
    }
}
