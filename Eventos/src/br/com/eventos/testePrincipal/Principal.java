package br.com.eventos.testePrincipal;

import java.text.ParseException;
import java.util.Calendar;

import br.com.eventos.dao.EventoDao;
import br.com.eventos.exception.DataIvalidaException;
import br.com.eventos.modelo.Evento;

public class Principal {

	public static void main(String[] args) throws DataIvalidaException, ParseException {
		
		Evento evento = new Evento();
		
		evento.setNome("Teste");
		evento.setDescricao("descricao");
		evento.setDataEvento(Calendar.getInstance());
		evento.setPreco(45);
		
		EventoDao dao = new EventoDao();
		dao.adiciona(evento);
	}

}
