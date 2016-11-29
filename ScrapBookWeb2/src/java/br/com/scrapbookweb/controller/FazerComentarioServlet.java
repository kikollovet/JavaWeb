
package br.com.scrapbookweb.controller;

import br.com.scrapbookweb.dao.ComentarioDAO;
import br.com.scrapbookweb.dao.UsuarioDAO;
import br.com.scrapbookweb.exception.CampoVazioComentarioException;
import br.com.scrapbookweb.model.Comentario;
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

@WebServlet(name = "FazerComentarioServlet", urlPatterns = {"/fazerComentario"})
public class FazerComentarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_topico = Integer.parseInt(request.getParameter("id"));
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        String login = u.getLogin();
        
        Comentario coment = new Comentario();
        coment.setComentario(request.getParameter("comentario"));
        coment.setLogin(login);
        coment.setId_topico(id_topico);
        
        ComentarioDAO cDao = new ComentarioDAO();
        try {
            cDao.inserirComentario(coment);
            UsuarioDAO uDao = new UsuarioDAO();
            uDao.adicionarPontos(login, 3);
        } catch (CampoVazioComentarioException ex) {
            request.setAttribute("erro", ex.getMessage());
            request.getRequestDispatcher("exibeTopico?id=" + id_topico).forward(request, response);
        }
        
        response.sendRedirect("exibeTopico?id=" + id_topico);
    }

}
