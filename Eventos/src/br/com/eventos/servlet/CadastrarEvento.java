package br.com.eventos.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.eventos.dao.EventoDao;
import br.com.eventos.exception.CampoVazioException;
import br.com.eventos.exception.DataIvalidaException;
import br.com.eventos.modelo.Evento;

@WebServlet("/cadastrarEvento")
public class CadastrarEvento extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");
		String dataEmTexto = request.getParameter("data");
		String precoEmTexto = request.getParameter("preco");
		
		try {
			if(nome.isEmpty() || descricao.isEmpty() || dataEmTexto.isEmpty() || precoEmTexto.isEmpty()){
				throw new CampoVazioException("Todos os campos devem estar preenchidos para gravar no banco de dados");
			}
		} catch (CampoVazioException e) {
			request.setAttribute("erro", e.getMessage());
            request.getRequestDispatcher("cadastroEvento.jsp").forward(request, response);
		}
		
		
		Calendar dataEvento = null;
		
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
			dataEvento = Calendar.getInstance();
			dataEvento.setTime(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		} 
		
		
		int preco = Integer.parseInt(precoEmTexto);
		
		Evento evento = new Evento();
		evento.setNome(nome);
		evento.setDescricao(descricao);
		evento.setDataEvento(dataEvento);
		evento.setPreco(preco);
		
		EventoDao dao = new EventoDao();
		
		try {
			dao.adiciona(evento);
		} catch (DataIvalidaException e) {
			request.setAttribute("erro", e.getMessage());
            request.getRequestDispatcher("cadastroEvento.jsp").forward(request, response);
		} 
		
		//request.getRequestDispatcher("cadastroSucesso.jsp").forward(request, response);
		response.sendRedirect("cadastroSucesso.jsp");
	}
}
