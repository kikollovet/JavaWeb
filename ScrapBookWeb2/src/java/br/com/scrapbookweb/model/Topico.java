
package br.com.scrapbookweb.model;

public class Topico {
    
    private int id_topico;
    private String titulo;
    private String conteudo;
    private String login;

    public int getId_topico() {
        return id_topico;
    }

    public void setId_topico(int id_topico) {
        this.id_topico = id_topico;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "Topico{" + "id_topico=" + id_topico + ", titulo=" + titulo + ", conteudo=" + conteudo + ", login=" + login + '}';
    }
                 
}
