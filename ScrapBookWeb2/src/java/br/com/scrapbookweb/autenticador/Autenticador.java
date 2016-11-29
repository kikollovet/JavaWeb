
package br.com.scrapbookweb.autenticador;

import br.com.scrapbookweb.exception.NaoFoiPossivelEfetuarLoginException;
import br.com.scrapbookweb.model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Autenticador {
    
    static{
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Autenticador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Usuario autenticar(String login, String senha) throws Exception{
        Usuario u = new Usuario();
        try(Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/scrapbookweb","postgres","admin")){
            
            PreparedStatement ps = c.prepareStatement("select * from usuario where login = ? and senha = ?");
            ps.setString(1, login);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                u.setLogin(rs.getString("login"));
                u.setEmail(rs.getString("email"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                u.setPontos(rs.getInt("pontos"));
                return u;
            } else {
                throw new NaoFoiPossivelEfetuarLoginException("Não foi possível autentificar o usuário!");
            }
        }
    }
}
