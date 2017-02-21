package br.com.eventos.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.mysql.jdbc.PreparedStatement;

import br.com.eventos.exception.CampoVazioException;
import br.com.eventos.exception.DataIvalidaException;
import br.com.eventos.jdbc.ConnectionFactory;
import br.com.eventos.modelo.Evento;

public class EventoDao {

	private Connection connection;
	
	public EventoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Evento evento) throws DataIvalidaException{
		String sql = "insert into eventos " + "(nome,descricao,dataevento,preco)" +
				" values (?,?,?,?)";
		
		if(evento.verificaData(evento)){
			throw new DataIvalidaException("Somente pode adicionar eventos com data de hoje ou no futuro");
		}
		
		try {
			java.sql.PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, evento.getNome());
			stmt.setString(2, evento.getDescricao());
			stmt.setDate(3, new Date(evento.getDataEvento().getTimeInMillis()));
			stmt.setInt(4, evento.getPreco());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
