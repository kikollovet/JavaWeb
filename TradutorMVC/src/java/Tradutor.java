
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Tradutor {
    
    public String traduzir(String palavra){
        try {
            FileReader arq = new FileReader("c:/Kiko/Programação/ITA/Curso 3 NetBeans/TradutorMVC/bancoPalavras.txt");
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            String[] array = linha.split("->");
            if(array[0].equalsIgnoreCase(palavra)){
                return array[1];
            }
            while (linha != null) {
                linha = lerArq.readLine();
                array = linha.split("->");
                if(array[0].equalsIgnoreCase(palavra)){
                    return array[1];
                }
            }
 
            arq.close();
        } catch (IOException e) {
        System.err.printf("Erro na abertura do arquivo: %s.\n",
            e.getMessage());
        } catch (NullPointerException e) {
            return palavra;
        }
        return palavra;
    }
}
