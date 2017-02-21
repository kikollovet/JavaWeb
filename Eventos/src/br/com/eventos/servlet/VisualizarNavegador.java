package br.com.eventos.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/visualizarNavegador")
public class VisualizarNavegador extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");
		String dataEvento = request.getParameter("data");
		String preco = request.getParameter("preco");
		
		request.setAttribute("nome", nome);
		request.setAttribute("descricao", descricao);
		request.setAttribute("data", dataEvento);
		request.setAttribute("preco", preco);
		
		request.getRequestDispatcher("eventoNavegador.jsp").forward(request, response);
	}
}
