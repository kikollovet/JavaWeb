
package br.com.scrapbookweb.dao;

import br.com.scrapbookweb.exception.CampoVazioComentarioException;
import br.com.scrapbookweb.model.Comentario;
import br.com.scrapbookweb.model.Topico;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComentarioDAO {
    
    static{
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void inserirComentario(Comentario coment) throws CampoVazioComentarioException{
        
        try(Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/scrapbookweb","postgres","admin")){
            
            if(coment.getComentario().isEmpty()){
                throw new CampoVazioComentarioException("Atenção!!! O campo comentário deve estar preenchido!");
            }
            
            String sql ="INSERT INTO comentario(comentario, login, id_topico) VALUES (?, ?, ?);";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, coment.getComentario());
            stm.setString(2, coment.getLogin());
            stm.setInt(3, coment.getId_topico());
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Comentario> listaComentarioPorIdTopico(int id_topico) {
        List<Comentario> listaComentario = new ArrayList<>();
        
        try(Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/scrapbookweb","postgres","admin")){
            
            String sql ="SELECT * FROM comentario WHERE id_topico = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, id_topico);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Comentario coment = new Comentario();
                coment.setId_comentario(rs.getInt("id_comentario"));
                coment.setComentario(rs.getString("comentario"));
                coment.setLogin(rs.getString("login"));
                coment.setId_topico(rs.getInt("id_topico"));
                listaComentario.add(coment);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaComentario;
    }
}
