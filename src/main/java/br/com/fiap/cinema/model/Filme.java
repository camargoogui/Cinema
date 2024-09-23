//Guilherme Paes Camargo RM555166
//Icaro Américo de Albuquerque Lima RM555131
package br.com.fiap.cinema.model;

public class Filme {
    private String titulo;
    private String ano;
    private String diretor;

    // Construtores, Getters e Setters
    public Filme(String titulo, String ano, String diretor) {
        this.titulo = titulo;
        this.ano = ano;
        this.diretor = diretor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }
}
