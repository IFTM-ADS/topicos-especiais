/*package modelo.dao;


import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import java.sql.SQLException;
import modelo.Bebida;
import modelo.dao.BebidaDAO;
import modelo.dao.DAOFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BebidaDAOTest {

    private static final DbSetupTracker dbSetupTracker = new DbSetupTracker();
    private DAOFactory factory;
    private BebidaDAO dao;
    private Bebida bebida;
    private Bebida bebida_result;

    @Before
    public void setUp() throws SQLException {
        Operation operation
                = sequenceOf(OperacoesComunsBD.LIMPA_TUDO,
                        //OperacoesComunsBD.RESET_AUTOINCREMENT_MYSQL,
                        // OperacoesComunsBD.RESET_AUTOINCREMENT_H2,                             
                        OperacoesComunsBD.INSERE_DADOS_BASICOS);

        // MySQL
//        ConexaoFactory.getConexaoMySQL(); 
//        DbSetup dbSetup = new DbSetup(new DriverManagerDestination(
//                "jdbc:mysql://localhost:3306/db_restaurante",
//                "root", "12345"), operation);
        //H2 em arquivo 
        //ConexaoFactory.getConexaoH2Arquivo();  
        ////para criar o BD caso ele nao exista 
        //DbSetup dbSetup = new DbSetup(new DriverManagerDestination( 
        //"jdbc:h2:./db_restaurante;
        //DATABASE_TO_UPPER=false;", 
        //"root", "12345"), operation);        
        //H2 em memoria 
        DbSetup dbSetup = new DbSetup(new DriverManagerDestination(
                "jdbc:h2:mem:db_restaurante;DATABASE_TO_UPPER=false;DB_CLOSE_DELAY=-1;"
                + "INIT=CREATE TABLE IF NOT EXISTS "
                + "bebida "
                + "(codigo int(11) PRIMARY KEY, "
                + "nome varchar(45), "
                + "preco double, "
                + "descricao varchar(45));",
                "root", "12345"), operation);

        dbSetupTracker.launchIfNecessary(dbSetup);
        factory = new DAOFactory();
        factory.abrirConexao();
        dao = factory.criarBebidaDAO();
    }

    @After
    public void tearDown() throws SQLException {
        factory.fecharConexao();
    }

    public BebidaDAOTest() {
    }

    @Test
    public void testGravar() throws Exception {
        System.out.println("gravar");

        bebida = new Bebida();

        bebida.setCodigo(2210);
        bebida.setNome("bebida_test10");
        bebida.setPreco(20.00);
        bebida.setDescricao("bebida_test10");

        dao.gravar(bebida);

        bebida_result = dao.buscar(2210);
        assertEquals(bebida.getNome(), bebida_result.getNome());
    }

    @Test
    public void testBuscar() throws Exception {
        System.out.println("buscar");

        bebida_result = dao.buscar(2210);

        assertEquals(bebida.getCodigo_bebida(), bebida_result.getCodigo_bebida());
    }

    @Test
    public void testAtualizar() throws Exception {
        System.out.println("atualizar");

        bebida.setNome("cachaça");

        dao.atualizar(bebida);

        bebida_result = dao.buscar(2210);

        assertEquals(bebida.getNome(), bebida_result.getNome());
    }

    @Test
    public void testRemover() throws Exception {
        System.out.println("remover");

        dao.remover(bebida);

        assertNull(dao.buscar(2210));
    }

    @Test
    public void testBuscarIdInexistente() throws SQLException {
        assertNull(dao.buscar(5));
    }

    @Test
    public void testBuscarTodos() throws Exception {
        assertNotNull(dao.buscarTodos());
    }
}
*/