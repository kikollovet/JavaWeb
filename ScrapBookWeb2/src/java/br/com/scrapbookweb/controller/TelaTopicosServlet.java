
package br.com.scrapbookweb.controller;

import br.com.scrapbookweb.comparador.ComparadorTopico;
import br.com.scrapbookweb.dao.TopicoDAO;
import br.com.scrapbookweb.dao.UsuarioDAO;
import br.com.scrapbookweb.model.Topico;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TelaTopicosServlet", urlPatterns = {"/telaTopicos"})
public class TelaTopicosServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        TreeMap<Topico, String> mapa = new TreeMap(new ComparadorTopico());
        TopicoDAO tDao = new TopicoDAO();
        UsuarioDAO uDao = new UsuarioDAO();
        List<Topico> lista = tDao.listaTopicos();
        
        for(Topico t: lista){
            String login = t.getLogin();
            String nome = uDao.recuperarUsuario(login).getNome();
            mapa.put(t, nome);
        }
        
        request.setAttribute("mapa", mapa);
        
        request.getRequestDispatcher("telaTopicos.jsp").forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        TreeMap<Topico, String> mapa = new TreeMap(new ComparadorTopico());
        TopicoDAO tDao = new TopicoDAO();
        UsuarioDAO uDao = new UsuarioDAO();
        List<Topico> lista = tDao.listaTopicos();
        
        for(Topico t: lista){
            String login = t.getLogin();
            String nome = uDao.recuperarUsuario(login).getNome();
            mapa.put(t, nome);
        }
        
        request.setAttribute("mapa", mapa);
        
        request.getRequestDispatcher("telaTopicos.jsp").forward(request, response);
    }

}
