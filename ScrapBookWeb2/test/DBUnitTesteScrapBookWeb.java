
import br.com.scrapbookweb.autenticador.Autenticador;
import br.com.scrapbookweb.dao.ComentarioDAO;
import br.com.scrapbookweb.dao.TopicoDAO;
import br.com.scrapbookweb.dao.UsuarioDAO;
import br.com.scrapbookweb.exception.CampoVazioCadastroException;
import br.com.scrapbookweb.exception.CampoVazioComentarioException;
import br.com.scrapbookweb.exception.CampoVazioTopicoException;
import br.com.scrapbookweb.exception.LoginUsuarioExistenteException;
import br.com.scrapbookweb.exception.NaoFoiPossivelEfetuarLoginException;
import br.com.scrapbookweb.model.Comentario;
import br.com.scrapbookweb.model.Topico;
import br.com.scrapbookweb.model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class DBUnitTesteScrapBookWeb {
    
   JdbcDatabaseTester jdt;
    
    @Before
    public void setUp() throws Exception{
        
        jdt = new JdbcDatabaseTester("org.postgresql.Driver","jdbc:postgresql://localhost/scrapbookweb","postgres","admin");
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        jdt.setDataSet(loader.load("/inicio.xml"));
        jdt.onSetup();
        
        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/scrapbookweb","postgres","admin");Statement statement = connection.createStatement()){
            statement.execute("alter sequence " + "topico_id_topico_seq" + " RESTART WITH 4");
            statement.execute("alter sequence " + "comentario_id_comentario_seq" + " RESTART WITH 4");
        }
    }
    
    @Test
    public void adicionaUsuarioUuarioDAO() throws Exception {
        Usuario u = new Usuario();
        u.setLogin("lara");
        u.setEmail("lara@email.com.br");
        u.setNome("Lara Nunes");
        u.setSenha("lara");
        u.setPontos(20);
        
        UsuarioDAO dao = new UsuarioDAO();
        dao.inserirUsuario(u);
        
        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("USUARIO");
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        IDataSet expectedDataSet = loader.load("/verifica.xml");
        ITable expectedTable = expectedDataSet.getTable("USUARIO");
        Assertion.assertEquals(expectedTable, currentTable);
    }
    
    @Test
    public void testeRankingUsuarioDAO() {
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> lista = dao.ranking();
        assertEquals(3, lista.size());
        assertEquals("julia", lista.get(0).getLogin());
        assertEquals("pedro", lista.get(1).getLogin());
        assertEquals("joao", lista.get(2).getLogin());
    }
    
    @Test
    public void testeRecuperarUsuarioDAO(){
        UsuarioDAO dao = new UsuarioDAO();
        assertEquals(dao.recuperarUsuario("julia").getNome(), "Julia Maria");
    }
    
    @Test
    public void testeAdicionaPontosUsuarioDAO(){
        UsuarioDAO dao = new UsuarioDAO();
        assertEquals(dao.recuperarUsuario("julia").getPontos(), 15);
        dao.adicionarPontos("julia", 15);
        assertEquals(dao.recuperarUsuario("julia").getPontos(), 30);
    }
    
    @Test
    public void adicionaTopicoDAO() throws Exception {
        Topico t = new Topico();
        
        t.setConteudo("4");
        t.setTitulo("teste 4");
        t.setLogin("julia");
        
        TopicoDAO dao = new TopicoDAO();
        dao.inserirTopico(t);
        
        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("TOPICO");
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        IDataSet expectedDataSet = loader.load("/verifica.xml");
        ITable expectedTable = expectedDataSet.getTable("TOPICO");
        Assertion.assertEquals(expectedTable, currentTable);
    }
    
    @Test
    public void testeRecuperarTopicoDAO(){
        TopicoDAO dao = new TopicoDAO();
        assertEquals(dao.recuperarTopico(1).getTitulo(), "teste 1");
    }
    
    @Test
    public void testeListaTopicosDAO(){
        TopicoDAO dao = new TopicoDAO();
        List<Topico> lista = dao.listaTopicos();
        assertEquals(3, lista.size());
        assertEquals("julia", lista.get(0).getLogin());  
    }
    
    @Test
    public void inserirComentarioDAO() throws Exception {
        Comentario coment = new Comentario();
        
        coment.setId_topico(1);
        coment.setLogin("joao");
        coment.setComentario("quatro");
        
        ComentarioDAO dao = new ComentarioDAO();
        dao.inserirComentario(coment);
        
        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("COMENTARIO");
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        IDataSet expectedDataSet = loader.load("/verifica.xml");
        ITable expectedTable = expectedDataSet.getTable("COMENTARIO");
        Assertion.assertEquals(expectedTable, currentTable);
    }
    
    @Test
    public void testeListaComentarioPorIdTopico(){
        ComentarioDAO dao = new ComentarioDAO();
        List<Comentario> lista = dao.listaComentarioPorIdTopico(1);
        assertEquals(2, lista.size());
        assertEquals("joao", lista.get(0).getLogin()); 
    }
    
    @Test
    public void autenticarSucesso() throws Exception{
        Autenticador a = new Autenticador();
        Usuario u = a.autenticar("joao", "joao");
        assertEquals(u.getNome(), "Joao Pedro");
    }
    
    @Test
    public void naoFoiPossivelEfetuarLoginException() throws Exception{
        Autenticador a = new Autenticador();
        try{
            a.autenticar("joao", "senhaerrada");
            fail();
        } catch (NaoFoiPossivelEfetuarLoginException e){
            assertEquals(e.getMessage(), "Não foi possível autentificar o usuário!");
        }
    }
    
    @Test(expected = LoginUsuarioExistenteException.class)
    public void loginUsuarioExistenteException() throws Exception{
        Usuario u = new Usuario();
        u.setLogin("joao");
        u.setNome("Joao Macedo");
        u.setEmail("joaomacedo@email.com.br");
        u.setSenha("qqrsenha");
        u.setPontos(0);
        
        UsuarioDAO dao = new UsuarioDAO();
        dao.inserirUsuario(u);
    }
    
    @Test(expected = CampoVazioCadastroException.class)
    public void campoVazioCadastroException() throws Exception{
        Usuario u = new Usuario();
        u.setLogin("joaodois");
        u.setNome("");
        u.setEmail("");
        u.setSenha("qqrsenha");
        u.setPontos(0);
        
        UsuarioDAO dao = new UsuarioDAO();
        dao.inserirUsuario(u);
    }
    
    @Test(expected = CampoVazioTopicoException.class)
    public void campoVazioTopicoException() throws CampoVazioTopicoException{
        Topico t = new Topico();
        t.setId_topico(5);
        t.setLogin("joao");
        t.setConteudo("");
        t.setTitulo("");
        
        TopicoDAO dao = new TopicoDAO();
        dao.inserirTopico(t);
    }
    
    @Test(expected = CampoVazioComentarioException.class)
    public void campoVazioComentarioException() throws CampoVazioComentarioException{
        Comentario coment = new Comentario();
        coment.setLogin("joao");
        coment.setId_topico(1);
        coment.setComentario("");
        
        ComentarioDAO dao = new ComentarioDAO();
        dao.inserirComentario(coment);
    }
}
