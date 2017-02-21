package br.com.eventos.mvc.logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.eventos.dao.EventoDao;
import br.com.eventos.exception.CampoVazioException;
import br.com.eventos.exception.DataIvalidaException;
import br.com.eventos.modelo.Evento;

public class CadastrarEventoLogica implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String nome = req.getParameter("nome");
		String descricao = req.getParameter("descricao");
		String dataEmTexto = req.getParameter("data");
		String precoEmTexto = req.getParameter("preco");
		
		try {
			if(nome.isEmpty() || descricao.isEmpty() || dataEmTexto.isEmpty() || precoEmTexto.isEmpty()){
				throw new CampoVazioException("Todos os campos devem estar preenchidos para gravar no banco de dados");
			}
		} catch (CampoVazioException e) {
			req.setAttribute("erro", e.getMessage());
            return "cadastroEvento.jsp";
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
			req.setAttribute("erro", e.getMessage());
            return "cadastroEvento.jsp";
		} 
		
		return "cadastroSucesso.jsp";
	}

}
