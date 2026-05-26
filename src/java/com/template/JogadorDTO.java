package com.template;

/*  FutebolDTO é a classe que guarda os atributos e os gets and setters do jogador.
    Utilizamos ela para criar objetos jogadores.
*/

public class JogadorDTO {
    private int id;
    private String nome;
    private int idade;
    private double valor;
    private String desempenho;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(String desempenho) {
        this.desempenho = desempenho;
    }
}
