
package br.com.scrapbookweb.controller;

import br.com.scrapbookweb.dao.UsuarioDAO;
import br.com.scrapbookweb.exception.CampoVazioCadastroException;
import br.com.scrapbookweb.exception.LoginUsuarioExistenteException;
import br.com.scrapbookweb.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EfetuarCadastroServlet", urlPatterns = {"/efetuarCadastro"})
public class EfetuarCadastroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario u = new Usuario();
        u.setLogin(request.getParameter("login"));
        u.setEmail(request.getParameter("email"));
        u.setNome(request.getParameter("nome"));
        u.setSenha(request.getParameter("senha"));
        u.setPontos(0);
        try{
            UsuarioDAO dao = new UsuarioDAO();
            dao.inserirUsuario(u);
        } catch (LoginUsuarioExistenteException e) {
            request.setAttribute("erro", e.getMessage());
            request.getRequestDispatcher("telaCadastro.jsp").forward(request, response);
        } catch (CampoVazioCadastroException ecx) {
            request.setAttribute("erro", ecx.getMessage());
            request.getRequestDispatcher("telaCadastro.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EfetuarCadastroServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("index.html").forward(request, response);
    }

}
