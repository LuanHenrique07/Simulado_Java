package br.edu.up.daos;

import br.edu.up.model.Pessoa;

import java.io.*;
import java.util.*;

public class GerenciadorDeArquivos {
    private final String Pessoas = "A.csv";
    private final String Cidades = "B.csv";
    private final String Unificado = "C.csv";

    public void adicionarPessoa(Pessoa pessoa) {
        if (!codigoExiste(pessoa.getCodigo(), Pessoas)) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(Pessoas, true))) {
                bw.write(pessoa.toCSVPessoa());
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Erro: Código já existe para outra pessoa.");
        }
    }

    public void adicionarCidade(Pessoa pessoa) {
        if (codigoExiste(pessoa.getCodigo(), Pessoas)) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(Cidades, true))) {
                bw.write(pessoa.toCSVCidade());
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Erro: Código de pessoa não encontrado.");
        }
    }

    public void unirArquivos() {
        Map<Integer, Pessoa> pessoasMap = new HashMap<>();
        Map<Integer, Pessoa> cidadesMap = new HashMap<>();

        // Ler dados do arquivo Pessoas
        List<String> pessoas = lerArquivo(Pessoas);
        for (String linha : pessoas) {
            String[] partes = linha.split(",");
            int codigo = Integer.parseInt(partes[0]);
            String nome = partes[1];
            pessoasMap.put(codigo, new Pessoa(codigo, nome, "", ""));
        }

        // Ler dados do arquivo Cidades
        List<String> cidades = lerArquivo(Cidades);
        for (String linha : cidades) {
            String[] partes = linha.split(",");
            String rua = partes[0];
            String cidade = partes[1];
            int codigo = Integer.parseInt(partes[2]);
            cidadesMap.put(codigo, new Pessoa(codigo, "", cidade, rua));
        }

        // Unir os dados e escrever no arquivo Unificado
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Unificado))) {
            for (Map.Entry<Integer, Pessoa> entry : pessoasMap.entrySet()) {
                int codigo = entry.getKey();
                Pessoa pessoa = entry.getValue();
                if (cidadesMap.containsKey(codigo)) {
                    Pessoa cidade = cidadesMap.get(codigo);
                    String linhaUnificada = codigo + "," + pessoa.getNome() + "," + cidade.getRua() + "," + cidade.getCidade();
                    bw.write(linhaUnificada);
                    bw.newLine();
                    // Mostrar no terminal
                    System.out.println(linhaUnificada);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> lerArquivo(String nomeArquivo) {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linhas;
    }

    private boolean codigoExiste(int codigo, String nomeArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (Integer.parseInt(partes[0]) == codigo) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
