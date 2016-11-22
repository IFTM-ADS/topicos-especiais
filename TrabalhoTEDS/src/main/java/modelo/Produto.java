package modelo;

public class Produto {

    private long codigo;
    private String nome;
    private int prato_codigo;
    private int bebida_codigo;
    private double preco;


    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getProduto_Prato_codigo() {
        return prato_codigo;
    }

    public void setProduto_Prato_codigo(int prato_codigo) {
        this.prato_codigo = prato_codigo;
    }

    public int getProduto_Bebida_codigo() {
        return bebida_codigo;
    }

    public void setProduto_Bebida_codigo(int bebida_codigo) {
        this.bebida_codigo = bebida_codigo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

}
