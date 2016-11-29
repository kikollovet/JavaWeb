
package br.com.scrapbookweb.controller;

import br.com.scrapbookweb.autenticador.Autenticador;
import br.com.scrapbookweb.exception.NaoFoiPossivelEfetuarLoginException;
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

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        
        Autenticador a = new Autenticador();
        try {
            Usuario usuario = a.autenticar(login, senha);
            request.getSession().setAttribute("usuario", usuario);
            request.getRequestDispatcher("telaTopicos").forward(request, response);
        } catch (NaoFoiPossivelEfetuarLoginException ex) {
            request.setAttribute("erro", ex.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
