
import java.util.List;
import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class TesteAcessoBancoDeDadosUsuario {
    
    JdbcDatabaseTester jdt;
    
    @Before
    public void setUp() throws Exception{
        
        jdt = new JdbcDatabaseTester("org.postgresql.Driver","jdbc:postgresql://localhost/coursera","postgres","admin");
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        jdt.setDataSet(loader.load("/inicio.xml"));
        jdt.onSetup();
    }
    
    @Test
    public void adicionaUsuario() throws Exception {
        Usuario u = new Usuario();
        u.setLogin("lara");
        u.setEmail("lara@email.com.br");
        u.setNome("Lara Nunes");
        u.setSenha("lara");
        u.setPontos(20);
        
        AcessoBancoDeDadosUsuario abu = new AcessoBancoDeDadosUsuario();
        abu.inserir(u);
        
        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("USUARIO");
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        IDataSet expectedDataSet = loader.load("/verifica.xml");
        ITable expectedTable = expectedDataSet.getTable("USUARIO");
        Assertion.assertEquals(expectedTable, currentTable);
    }
    
    @Test
    public void testeRanking() {
        AcessoBancoDeDadosUsuario abu = new AcessoBancoDeDadosUsuario();
        List<Usuario> lista = abu.ranking();
        assertEquals(3, lista.size());
        assertEquals("julia", lista.get(0).getLogin());
        assertEquals("pedro", lista.get(1).getLogin());
        assertEquals("joao", lista.get(2).getLogin());
    }
    
    @Test
    public void testeRecuperar(){
        AcessoBancoDeDadosUsuario abu = new AcessoBancoDeDadosUsuario();
        assertEquals(abu.recuperar("julia").getNome(), "Julia Maria");
    }
    
    @Test
    public void testeAdicionaPontos(){
        AcessoBancoDeDadosUsuario abu = new AcessoBancoDeDadosUsuario();
        assertEquals(abu.recuperar("julia").getPontos(), 15);
        abu.adicionarPontos("julia", 15);
        assertEquals(abu.recuperar("julia").getPontos(), 30);
    }
    
    
}
