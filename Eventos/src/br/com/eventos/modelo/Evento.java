package br.com.eventos.modelo;

import java.util.Calendar;

public class Evento {

	private String nome;
	private String descricao;
	private Calendar dataEvento;
	private int preco;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Calendar getDataEvento() {
		return dataEvento;
	}
	
	public void setDataEvento(Calendar dataEvento) {
		this.dataEvento = dataEvento;
	}
	
	public int getPreco() {
		return preco;
	}
	
	public void setPreco(int preco) {
		this.preco = preco;
	}
	
	public boolean verificaData(Evento evento) {
		boolean aDataVale = false;
		
		Calendar diaAtual = Calendar.getInstance();
	    diaAtual.set(Calendar.HOUR_OF_DAY, 0);
	    diaAtual.set(Calendar.MINUTE, 0);
	    diaAtual.set(Calendar.SECOND, 0);
	    diaAtual.set(Calendar.MILLISECOND, 0);
	    
	    Calendar diaEvento = evento.getDataEvento();
	    diaEvento.set(Calendar.HOUR_OF_DAY, 0);
	    diaEvento.set(Calendar.MINUTE, 0);
	    diaEvento.set(Calendar.SECOND, 0);
	    diaEvento.set(Calendar.MILLISECOND, 0);
		
		if(diaAtual.equals(diaEvento)){
			aDataVale = false;
		} else if (evento.getDataEvento().before(Calendar.getInstance())){
			aDataVale = true;
		} else if (evento.getDataEvento().after(Calendar.getInstance())){
			aDataVale = false;
		}
		
		return aDataVale;
	}
}
