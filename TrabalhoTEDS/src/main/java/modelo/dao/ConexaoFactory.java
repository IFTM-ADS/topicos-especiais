package modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoFactory {

    public static Connection getConexaoMySQL() throws SQLException {
        String caminho = "jdbc:mysql"; // "jdbc:postgresql"
        String host = "localhost";
        String porta = "3306"; // "5432"
        String bd = "db_restaurante";
        String login = "root"; // "postgres"
        String senha = "12345";
        String encoding = "?useUnicode=true&amp;characterEncoding=UTF-8";
        String url = caminho + "://" + host + ":" + porta + "/" + bd + encoding;

        Connection conexao = null;

        try {
            System.out.println("Conectando com o banco de dados.");
            Class.forName("com.mysql.jdbc.Driver"); // "org.postgresql.Driver"
            conexao = DriverManager.getConnection(url, login, senha);
            System.out.println("Conexão com o banco de dados estabelecida.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro ao carregar o driver JDBC.");
        }

        return conexao;
    }

    public static Connection getConexaoH2Memoria() throws SQLException {
        String bd = "db_restaurante";
        String url = "jdbc:h2:mem:" + bd + ";DATABASE_TO_UPPER=false;DB_CLOSE_DELAY=-1;"
                + "INIT=CREATE TABLE IF NOT EXISTS bebida (codigo int(11) PRIMARY KEY, "
                + "nome varchar(45), "
                + "preco double, "
                + "descricao varchar(45));";
        String login = "root";
        String senha = "12345";
        boolean criarBD = false;
        Connection conexao = null;
        try {
            Class.forName("org.h2.Driver");
            conexao = DriverManager.getConnection(url, login, senha);
        } catch (SQLException e) {
            System.out.println("Não foi possível estabelecer a conexão com o banco selecionado.");
            DAOFactory.mostrarSQLException(e);
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possível carregar o driver JDBC do BD.");
        }
        return conexao;
    }

    private static void criarTabelas(Connection conexao) throws SQLException {
        String create = "CREATE TABLE bebida (codigo nt(11), "
                + "nome varchar(45), "
                + "preco double, "
                + "descricao varchar(45));";
        try (Statement stmt = conexao.createStatement()) {
            int resultado = stmt.executeUpdate(create);
        }
    }

    private static void inserirDadosIniciais(Connection conexao) throws SQLException {
        String insert = "INSERT INTO bebida (codigo, nome, preco, descricao) "
                + "VALUES(2005, 'cachaça', '10.00', 'pinga');";
        try (Statement stmt = conexao.createStatement()) {
            int resultado = stmt.executeUpdate(insert);
        }
    }

    private ConexaoFactory() {
    }
}
