
package br.com.scrapbookweb.controller;

import br.com.scrapbookweb.dao.TopicoDAO;
import br.com.scrapbookweb.dao.UsuarioDAO;
import br.com.scrapbookweb.exception.CampoVazioTopicoException;
import br.com.scrapbookweb.model.Topico;
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

@WebServlet(name = "InserirTopicoServlet", urlPatterns = {"/inserirTopico"})
public class InserirTopicoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        String login = u.getLogin();
        
        Topico t = new Topico();
        t.setTitulo(request.getParameter("titulo"));
        t.setConteudo(request.getParameter("conteudo"));
        t.setLogin(login);
        
        TopicoDAO tDao = new TopicoDAO();
        try {
            tDao.inserirTopico(t);
            UsuarioDAO uDao = new UsuarioDAO();
            uDao.adicionarPontos(login, 10);
        } catch (CampoVazioTopicoException ex) {
            request.setAttribute("erro", ex.getMessage());
            request.getRequestDispatcher("telaInsereTopico.jsp").forward(request, response);
        }
        
        response.sendRedirect("telaTopicos");
    }

}
