package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
}
