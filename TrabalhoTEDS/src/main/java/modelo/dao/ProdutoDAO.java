package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Bebida;
import modelo.Prato;
import modelo.Produto;

public class ProdutoDAO {

    private final Connection conexao;

    public ProdutoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public Produto buscar_bebida(long codigo) throws SQLException {
        Produto produto = null;
        String selecao = "SELECT * FROM produto WHERE bebida_codigo = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(selecao)) {
            pstmt.setLong(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto();
                    produto.setCodigo(rs.getLong(1));
                    produto.setProduto_Bebida_codigo(rs.getInt(2));
                    produto.setProduto_Prato_codigo(rs.getInt(3));
                    produto.setPreco(rs.getDouble(4));
                    produto.setNome(rs.getString(5));
                }
            }
        }
        return produto;
    }

    public Produto buscar_prato(long codigo) throws SQLException {
        Produto produto = null;
        String selecao = "SELECT * FROM produto WHERE prato_codigo = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(selecao)) {
            pstmt.setLong(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto();
                    produto.setCodigo(rs.getLong(1));
                    produto.setProduto_Bebida_codigo(rs.getInt(2));
                    produto.setProduto_Prato_codigo(rs.getInt(3));
                    produto.setPreco(rs.getDouble(4));
                    produto.setNome(rs.getString(5));
                }
            }
        }
        return produto;
    }

    public Produto buscar(long codigo) throws SQLException {
        Produto produto = null;
        String selecao = "SELECT * FROM produto WHERE codigo = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(selecao)) {
            pstmt.setLong(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto();
                    produto.setCodigo(rs.getLong(1));
                    produto.setProduto_Bebida_codigo(rs.getInt(2));
                    produto.setProduto_Prato_codigo(rs.getInt(3));
                    produto.setPreco(rs.getDouble(4));
                    produto.setNome(rs.getString(5));
                }
            }
        }
        return produto;
    }

    public List<Produto> buscarTodos() throws SQLException {
        Produto produto;
        List<Produto> produtos = new ArrayList<Produto>();
        String selecao = "SELECT * FROM produto";
        try (Statement stmt = conexao.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(selecao)) {
                while (rs.next()) {
                    produto = new Produto();
                    produto.setCodigo(rs.getLong(1));
                    produto.setProduto_Bebida_codigo(rs.getInt(2));
                    produto.setProduto_Prato_codigo(rs.getInt(3));
                    produto.setPreco(rs.getDouble(4));
                    produto.setNome(rs.getString(5));
                    produtos.add(produto);
                }
            }
        }
        return produtos;
    }

    public void gravar_bebida(Produto produto) throws SQLException {

        String insercao = "INSERT INTO produto (bebida_codigo, preco, nome) VALUES (?, ?, ?);";
        try (PreparedStatement pstmt = conexao.prepareStatement(insercao)) {
            pstmt.setInt(1, produto.getProduto_Bebida_codigo());
            pstmt.setDouble(2, produto.getPreco());
            pstmt.setString(3, produto.getNome());
            int resultado = pstmt.executeUpdate();
            if (resultado == 1) {
                System.out.println("\nInserção bem sucedida.");
            } else {
                System.out.println("A inserção não foi feita corretamente.");
            }
        }
    }

    public void gravar_prato(Produto produto) throws SQLException {

        String insercao = "INSERT INTO produto (prato_codigo, preco, nome) VALUES (?, ?, ?);";
        try (PreparedStatement pstmt = conexao.prepareStatement(insercao)) {
            pstmt.setInt(1, produto.getProduto_Prato_codigo());
            pstmt.setDouble(2, produto.getPreco());
            pstmt.setString(3, produto.getNome());
            int resultado = pstmt.executeUpdate();
            if (resultado == 1) {
                System.out.println("\nInserção bem sucedida.");
            } else {
                System.out.println("A inserção não foi feita corretamente.");
            }
        }
    }

    public void atualizar_bebida(Bebida bebida) throws SQLException {
        String alteracao = "UPDATE produto SET preco = ?, nome = ? WHERE bebida_codigo = ?;";
        try (PreparedStatement pstmt = conexao.prepareStatement(alteracao)) {
            pstmt.setDouble(1, bebida.getPreco());
            pstmt.setString(2, bebida.getNome());
            pstmt.setInt(3, bebida.getCodigo_bebida());
            int alteracoes = pstmt.executeUpdate();
            if (alteracoes == 1) {
                System.out.println("\nAlteracao bem sucedida.");
            } else {
                System.out.println("A alteracao não foi feita corretamente.");
            }
        }
    }

    public void atualizar_prato(Prato prato) throws SQLException {
        String alteracao = "UPDATE produto SET preco = ?, nome = ? WHERE prato_codigo = ?;";
        try (PreparedStatement pstmt = conexao.prepareStatement(alteracao)) {           
            pstmt.setDouble(1, prato.getPreco());
            pstmt.setString(2, prato.getNome());
            pstmt.setInt(3, prato.getCodigo_prato());
            int alteracoes = pstmt.executeUpdate();
            if (alteracoes == 1) {
                System.out.println("\nAlteracao bem sucedida.");
            } else {
                System.out.println("A alteracao não foi feita corretamente.");
            }
        }
    }

    public void remover_prato(Prato prato) throws SQLException {
        String remocao = "DELETE FROM produto WHERE prato_codigo = ?";
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

    public void remover_bebida(Bebida bebida) throws SQLException {
        String remocao = "DELETE FROM produto WHERE bebida_codigo = ?";
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
}
