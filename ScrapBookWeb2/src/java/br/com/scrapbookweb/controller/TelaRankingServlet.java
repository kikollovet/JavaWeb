
package br.com.scrapbookweb.controller;

import br.com.scrapbookweb.dao.UsuarioDAO;
import br.com.scrapbookweb.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TelaRankingServlet", urlPatterns = {"/telaRanking"})
public class TelaRankingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioDAO uDao = new UsuarioDAO();
        List<Usuario> listaRanking = uDao.ranking();
        
        request.setAttribute("listaRanking", listaRanking);
        
        request.getRequestDispatcher("telaRanking.jsp").forward(request, response);
    }

}
