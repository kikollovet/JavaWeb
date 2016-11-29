
package br.com.scrapbookweb.dao;

import br.com.scrapbookweb.exception.CampoVazioTopicoException;
import br.com.scrapbookweb.model.Topico;
import br.com.scrapbookweb.model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TopicoDAO {
    
    static{
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TopicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void inserirTopico(Topico t) throws CampoVazioTopicoException{
        
        try(Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/scrapbookweb","postgres","admin")){
            
            if(t.getTitulo().isEmpty() || t.getConteudo().isEmpty()){
                throw new CampoVazioTopicoException("Atenção!!! Os dois campos devem estar preenchidos!");
            }
            
            String sql ="INSERT INTO topico(titulo, conteudo, login) VALUES (?, ?, ?);";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, t.getTitulo());
            stm.setString(2, t.getConteudo());
            stm.setString(3, t.getLogin());
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public List<Topico> listaTopicos() {
        List<Topico> listaTopico = new ArrayList<>();
        
        try(Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/scrapbookweb","postgres","admin")){
            
            String sql ="SELECT * FROM topico ORDER BY ID_TOPICO DESC";
            PreparedStatement stm = c.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Topico t = new Topico();
                t.setId_topico(rs.getInt("id_topico"));
                t.setTitulo(rs.getString("titulo"));
                t.setConteudo(rs.getString("conteudo"));
                t.setLogin(rs.getString("login"));
                listaTopico.add(t);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaTopico;
    }
     
    public Topico recuperarTopico(int id_topico) {
        Topico t = new Topico();
        try(Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/scrapbookweb","postgres","admin")){
            
            String sql ="SELECT * FROM topico WHERE id_topico = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, id_topico);
            ResultSet rs = stm.executeQuery();
            rs.next();
                t.setId_topico(rs.getInt("id_topico"));
                t.setTitulo(rs.getString("titulo"));
                t.setConteudo(rs.getString("conteudo"));
                t.setLogin(rs.getString("login"));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
}
