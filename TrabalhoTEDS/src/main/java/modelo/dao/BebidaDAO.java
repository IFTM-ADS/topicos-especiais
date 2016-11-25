package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Bebida;

public class BebidaDAO {

    private final Connection conexao;

    public BebidaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public Bebida buscar(long codigo) throws SQLException {
        Bebida bebida = null;
        String selecao = "SELECT * FROM bebida WHERE codigo = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(selecao)) {
            pstmt.setLong(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    bebida = new Bebida();
                    bebida.setCodigo_bebida(rs.getInt(1));
                    bebida.setNome(rs.getString(2));
                    bebida.setPreco(rs.getDouble(3));
                    bebida.setDescricao(rs.getString(4));
                }
            }
        }
        return bebida;
    }

    public List<Bebida> buscarTodos() throws SQLException {
        Bebida bebida;
        List<Bebida> bebidas = new ArrayList<Bebida>();
        String selecao = "SELECT * FROM bebida";
        try (Statement stmt = conexao.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(selecao)) {
                while (rs.next()) {
                    bebida = new Bebida();
                    bebida.setCodigo_bebida(rs.getInt(1));
                    bebida.setNome(rs.getString(2));
                    bebida.setPreco(rs.getDouble(3));
                    bebida.setDescricao(rs.getString(4));
                    bebidas.add(bebida);
                }
            }
        }
        return bebidas;
    }

    public void gravar(Bebida bebida) throws SQLException {

        String insercao = "INSERT INTO bebida (codigo, nome, descricao, preco) VALUES (?, ?, ?, ?);";
        try (PreparedStatement pstmt = conexao.prepareStatement(insercao)) {
            pstmt.setInt(1, bebida.getCodigo_bebida());
            pstmt.setString(2, bebida.getNome());
            pstmt.setString(3, bebida.getDescricao());
            pstmt.setDouble(4, bebida.getPreco());
            int resultado = pstmt.executeUpdate();
            if (resultado == 1) {
                System.out.println("\nInserção bem sucedida.");
            } else {
                System.out.println("A inserção não foi feita corretamente.");
            }
        }
    }

    public void remover(Bebida bebida) throws SQLException {
        String remocao = "DELETE FROM bebida WHERE codigo = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(remocao)) {
            pstmt.setInt(1, bebida.getCodigo_bebida());
            int remocoes = pstmt.executeUpdate();
            if (remocoes == 1) {
                System.out.println("Remoção efetuada com sucesso.");
            } else {
                System.out.println("Não foi possível efetuar a remoção.");
            }
        }
    }

    public void atualizar(Bebida bebida) throws SQLException {
        String alteracao = "UPDATE bebida SET nome = ?, descricao = ?, preco = ? WHERE codigo = ?;";
        try (PreparedStatement pstmt = conexao.prepareStatement(alteracao)) {
            pstmt.setString(1, bebida.getNome());
            pstmt.setString(2, bebida.getDescricao());
            pstmt.setDouble(3, bebida.getPreco());
            pstmt.setInt(4, bebida.getCodigo_bebida());
            int alteracoes = pstmt.executeUpdate();
            if (alteracoes == 1) {
                System.out.println("\nAlteracao bem sucedida.");
            } else {
                System.out.println("A alteracao não foi feita corretamente.");
            }
        }
    }
}
