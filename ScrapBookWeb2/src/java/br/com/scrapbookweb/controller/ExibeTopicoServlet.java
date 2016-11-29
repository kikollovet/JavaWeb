
package br.com.scrapbookweb.controller;

import br.com.scrapbookweb.comparador.ComparadorComentario;
import br.com.scrapbookweb.dao.ComentarioDAO;
import br.com.scrapbookweb.dao.TopicoDAO;
import br.com.scrapbookweb.dao.UsuarioDAO;
import br.com.scrapbookweb.model.Comentario;
import br.com.scrapbookweb.model.Topico;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ExibeTopicoServlet", urlPatterns = {"/exibeTopico"})
public class ExibeTopicoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_topico = Integer.parseInt(request.getParameter("id"));
        TopicoDAO tDao = new TopicoDAO();
        Topico topico = tDao.recuperarTopico(id_topico);
        
        UsuarioDAO uDao = new UsuarioDAO();
        String loginDoTopico = tDao.recuperarTopico(id_topico).getLogin();
        String autorTopico = uDao.recuperarUsuario(loginDoTopico).getNome();
        
        request.setAttribute("autorTopico", autorTopico);
        request.setAttribute("topico", topico);
        
        ComentarioDAO cDao = new ComentarioDAO();
        List<Comentario> lista = cDao.listaComentarioPorIdTopico(id_topico);
        
        TreeMap<Comentario, String> mapa = new TreeMap(new ComparadorComentario());
        for(Comentario coment: lista){
            String login = coment.getLogin();
            String nome = uDao.recuperarUsuario(login).getNome();
            mapa.put(coment, nome);
        }
        request.setAttribute("mapa", mapa);
        
        request.getRequestDispatcher("exibeTopico.jsp").forward(request, response);
    }

}
