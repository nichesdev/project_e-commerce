package br.com.ecommerce;

import br.com.ecommerce.exception.EstoqueInsuficienteException;
import br.com.ecommerce.exception.ProdutoNaoEncontradoException;
import br.com.ecommerce.model.Produto;
import br.com.ecommerce.service.CarrinhoService;
import br.com.ecommerce.service.EstoqueService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EstoqueService estoque = new EstoqueService();
        CarrinhoService carrinho = new CarrinhoService();

        estoque.cadastrarProduto(new Produto(1L, "Teclado Mecânico", 250.00), 10);
        estoque.cadastrarProduto(new Produto(2L, "Mouse Gamer", 120.00), 5);
        estoque.cadastrarProduto(new Produto(3L, "Monitor 144Hz", 900.00), 2);

        boolean rodando = true;

        System.out.println("=== BEM-VINDO AO E-COMMERCE CLI ===");

        while (rodando) {
            System.out.println("\n--------------------------------");
            System.out.println("1 - Listar Estoque");
            System.out.println("2 - Adicionar Produto ao Carrinho");
            System.out.println("3 - Aplicar Cupom de Desconto");
            System.out.println("4 - Ver Carrinho e Total");
            System.out.println("5 - Finalizar Compra");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            try {
                switch (opcao) {
                    case 1:
                        estoque.listarEstoque();
                        break;

                    case 2:
                        System.out.print("Digite o ID do produto: ");
                        long id = scanner.nextLong();
                        System.out.print("Digite a quantidade: ");
                        int qtd = scanner.nextInt();

                        // Busca o produto (pode lançar ProdutoNaoEncontradoException)
                        Produto p = estoque.buscarProduto(id);

                        // Adiciona ao carrinho (pode lançar EstoqueInsuficienteException)
                        carrinho.adicionarItem(p, qtd, estoque);
                        break;

                    case 3:
                        System.out.print("Digite o código do cupom (Ex: DEV10, DEV20): ");
                        String cupom = scanner.next();
                        carrinho.aplicarCupom(cupom);
                        break;

                    case 4:
                        System.out.println("\n--- SUB-TOTAL DO CARRINHO ---");
                        System.out.printf("Subtotal: R$ %.2f\n", carrinho.calcularSubtotal());
                        System.out.printf("Total com Desconto: R$ %.2f\n", carrinho.calcularTotalFinal());
                        break;

                    case 5:
                        carrinho.finalizarCompra(estoque);
                        break;

                    case 0:
                        System.out.println("Saindo do sistema... Até logo!");
                        rodando = false;
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (ProdutoNaoEncontradoException | EstoqueInsuficienteException e) {
                System.out.println("\n[ERRO DE NEGÓCIO]: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\n[ERRO INESPERADO]: " + e.getMessage());
            }
        }

        scanner.close();
    }
}