
package br.com.scrapbookweb.model;

public class Comentario {
   
    private int id_comentario;
    private String comentario;
    private String login;
    private int id_topico;

    public int getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(int id_comentario) {
        this.id_comentario = id_comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId_topico() {
        return id_topico;
    }

    public void setId_topico(int id_topico) {
        this.id_topico = id_topico;
    }

    @Override
    public String toString() {
        return "Comentario{" + "id_comentario=" + id_comentario + ", comentario=" + comentario + ", login=" + login + ", id_topico=" + id_topico + '}';
    }
    
}
