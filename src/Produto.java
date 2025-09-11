public class Produto {
    private int codigo;
    private String nome;
    private float preco;

    Produto(){
        this.codigo=0;
        this.nome="NULO";
        this.preco=0;
    }

    public int getCodigo() {
        return this.codigo;
    }
    public String getNome() {
        return this.nome;
    }
    public float getPreco() {
        return this.preco;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto [codigo=" + this.codigo + ", nome=" + this.nome + ", preco=" + this.preco + "]";
    }
}
