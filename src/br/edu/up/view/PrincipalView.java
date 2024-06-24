package br.edu.up.view;

import br.edu.up.Prompt;
import br.edu.up.daos.GerenciadorDeArquivos;
import br.edu.up.model.Pessoa;

import java.util.Scanner;

public class PrincipalView {
    private GerenciadorDeArquivos gerenciadorDeArquivos = new GerenciadorDeArquivos();
    private Scanner scanner = new Scanner(System.in);

    public void MostrarMenu() {
        int op = 0;
        do {
            Prompt.limparConsole();
            Prompt.separador();
            Prompt.imprimir("MENU PRINCIPAL");
            Prompt.separador();

            Prompt.imprimir("1. Incluir Pessoas");
            Prompt.imprimir("2. Incluir Cidades");
            Prompt.imprimir("3. Listar pessoas e endereços");
            Prompt.imprimir("4. Sair");
            Prompt.separador();

            op = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha
            switch (op) {
                case 1:
                    incluirPessoa();
                    break;
                case 2:
                    incluirCidade();
                    break;
                case 3:
                    listarPessoasEEnderecos();
                    break;
                case 4:
                    Prompt.imprimir("Saindo...");
                    break;
                default:
                    Prompt.imprimir("Opção inválida!");
            }
            Prompt.imprimir("Pressione Enter para continuar...");
            scanner.nextLine();
        } while (op != 4);
    }

    private void incluirPessoa() {
        Prompt.imprimir("Incluir Pessoa");
        Prompt.separador();

        Prompt.imprimir("Código: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        Prompt.imprimir("Nome: ");
        String nome = scanner.nextLine();

        Pessoa pessoa = new Pessoa(codigo, nome, "", "");
        gerenciadorDeArquivos.adicionarPessoa(pessoa);
    }

    private void incluirCidade() {
        Prompt.imprimir("Incluir Cidade");
        Prompt.separador();

        Prompt.imprimir("Código da Pessoa: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        Prompt.imprimir("Cidade: ");
        String cidade = scanner.nextLine();

        Prompt.imprimir("Rua: ");
        String rua = scanner.nextLine();

        Pessoa pessoa = new Pessoa(codigo, "", cidade, rua);
        gerenciadorDeArquivos.adicionarCidade(pessoa);
    }

    private void listarPessoasEEnderecos() {
        Prompt.imprimir("Listar Pessoas e Endereços");
        Prompt.separador();
        gerenciadorDeArquivos.unirArquivos();
        Prompt.imprimir("Arquivos unificados com sucesso em C.csv");
    }
}
