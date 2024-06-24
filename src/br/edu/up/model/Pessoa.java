package br.edu.up.model;

public class Pessoa {
    public int Codigo;
    public String Nome;
    public String Cidade;
    public String Rua;

    public Pessoa(int codigo, String nome, String cidade, String rua) {
        Codigo = codigo;
        Nome = nome;
        Cidade = cidade;
        Rua = rua;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getRua() {
        return Rua;
    }

    public void setRua(String rua) {
        Rua = rua;
    }

    // Retorna o CSV apenas com o código e o nome
    public String toCSVPessoa() {
        return Codigo + "," + Nome;
    }

    // Retorna o CSV apenas com a rua, cidade e código
    public String toCSVCidade() {
        return Rua + "," + Cidade + "," + Codigo;
    }
}
