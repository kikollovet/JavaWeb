
package br.com.scrapbookweb.comparador;

import br.com.scrapbookweb.model.Topico;
import java.util.Comparator;

public class ComparadorTopico implements Comparator<Topico>{
    
    @Override
	public int compare(Topico um, Topico dois) {
	if (um.getId_topico() > dois.getId_topico()) {
            return -1;
        }
        if (um.getId_topico() < dois.getId_topico()) {
            return 1;
        }
	return 0;
    }
}
