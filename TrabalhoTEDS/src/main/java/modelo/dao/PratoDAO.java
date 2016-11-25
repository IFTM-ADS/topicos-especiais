package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Prato;

public class PratoDAO {

    private final Connection conexao;

    public PratoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public Prato buscar(long codigo) throws SQLException {
        Prato prato = null;
        String selecao = "SELECT * FROM prato WHERE codigo = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(selecao)) {
            pstmt.setLong(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    prato = new Prato();
                    prato.setCodigo_prato(rs.getInt(1));
                    prato.setNome(rs.getString(2));                   
                    prato.setPreco(rs.getDouble(3));
                    prato.setDescricao(rs.getString(4));
                }
            }
        }
        return prato;
    }

    public List<Prato> buscarTodos() throws SQLException {
        Prato prato;
        List<Prato> pratos = new ArrayList<Prato>();
        String selecao = "SELECT * FROM prato";
        try (Statement stmt = conexao.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(selecao)) {
                while (rs.next()) {
                    prato = new Prato();
                    prato.setCodigo_prato(rs.getInt(1));
                    prato.setNome(rs.getString(2));
                    prato.setPreco(rs.getDouble(3));
                    prato.setDescricao(rs.getString(4));
                    pratos.add(prato);
                }
            }
        }
        return pratos;
    }

    public void gravar(Prato prato) throws SQLException {

        String insercao = "INSERT INTO prato (codigo, nome, descricao, preco) VALUES (?, ?, ?, ?);";
        try (PreparedStatement pstmt = conexao.prepareStatement(insercao)) {
            pstmt.setInt(1, prato.getCodigo_prato());
            pstmt.setString(2, prato.getNome());
            pstmt.setString(3, prato.getDescricao());
            pstmt.setDouble(4, prato.getPreco());
            int resultado = pstmt.executeUpdate();
            if (resultado == 1) {
                System.out.println("\nInserção bem sucedida.");
            } else {
                System.out.println("A inserção não foi feita corretamente.");
            }
        }
    }

    public void remover(Prato prato) throws SQLException {
        String remocao = "DELETE FROM prato WHERE codigo = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(remocao)) {
            pstmt.setInt(1, prato.getCodigo_prato());
            int remocoes = pstmt.executeUpdate();
            if (remocoes == 1) {
                System.out.println("Remoção efetuada com sucesso.");
            } else {
                System.out.println("Não foi possível efetuar a remoção.");
            }
        }
    }

    public void atualizar(Prato prato) throws SQLException {
        String alteracao = "UPDATE prato SET nome = ?, descricao = ?, preco = ? WHERE codigo = ?;";
        try (PreparedStatement pstmt = conexao.prepareStatement(alteracao)) {         
            pstmt.setString(1, prato.getNome());        
            pstmt.setString(2, prato.getDescricao());
            pstmt.setDouble(3, prato.getPreco());
            pstmt.setInt(4, prato.getCodigo_prato());
            int alteracoes = pstmt.executeUpdate();
            if (alteracoes == 1) {
                System.out.println("\nAlteracao bem sucedida.");
            } else {
                System.out.println("A alteracao não foi feita corretamente.");
            }
        }
    }
}
