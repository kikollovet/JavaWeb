package br.com.eventos.servlet;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

import br.com.eventos.exception.DataIvalidaException;
import br.com.eventos.modelo.Evento;

@WebServlet("/gerarPdf")
public class GerarPdf extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	        
			String nome = request.getParameter("nome");
			String descricao = request.getParameter("descricao");
			String dataEvento = request.getParameter("data");
			String preco = request.getParameter("preco");
			
			Calendar dataEventoCalendar = null;
			
			try {
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataEvento);
				dataEventoCalendar = Calendar.getInstance();
				dataEventoCalendar.setTime(date);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		
			int precoInt = Integer.parseInt(preco);
			
			Evento evento = new Evento();
			evento.setNome(nome);
			evento.setDescricao(descricao);
			evento.setDataEvento(dataEventoCalendar);
			evento.setPreco(precoInt);
			
			try {
				if(evento.verificaData(evento)){
					throw new DataIvalidaException("O evento só pode ter a data de hoje ou uma data no futuro");
				}
			} catch (DataIvalidaException e) {
				request.setAttribute("erro", e.getMessage());
	            request.getRequestDispatcher("cadastroEvento.jsp").forward(request, response);
			}
			
			response.setContentType("application/pdf");
	        try {
	            
	            Document document = new Document();
	            
	            PdfWriter.getInstance(document, response.getOutputStream());
	            
	            document.open();
	            
	            document.add(new Paragraph("Dados sobre o evento"));
	            document.add(new Paragraph("   "));
	            document.add(new Paragraph("Nome do evento: " + nome));
	            document.add(new Paragraph("Descrição: " + descricao));
	            document.add(new Paragraph("Data do evento: " + dataEvento));
	            document.add(new Paragraph("Preço: R$" + preco + ",00"));
	            
	            document.close();
	        } catch (DocumentException de) {
	            throw new IOException(de.getMessage());
	        }
	    }
}
