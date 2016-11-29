
package br.com.scrapbookweb.comparador;

import br.com.scrapbookweb.model.Comentario;
import java.util.Comparator;

public class ComparadorComentario implements Comparator<Comentario>{
    
    @Override
	public int compare(Comentario um, Comentario dois) {
	if (um.getId_comentario()> dois.getId_comentario()) {
            return -1;
        }
        if (um.getId_comentario() < dois.getId_comentario()) {
            return 1;
        }
	return 0;
    }
}
