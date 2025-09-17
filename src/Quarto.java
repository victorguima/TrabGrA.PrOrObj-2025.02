import java.util.Arrays;

public class Quarto {
    private int numero;
    private char categoria;
    private float diaria;
    private int consumo[];
    private int nConsumo; //contador para o número de itens consumidos

    Quarto(){ //inicializa os atributos com valores nulos só pra garantir
        this.numero=0;
        this.categoria=' ';
        this.diaria=0;
        this.consumo=new int[5]; //inicializa o vetor consumo com limite 5
        this.nConsumo=0;
    }


    public int getNumero() {
        return this.numero;
    }
    public char getCategoria() {
        return this.categoria;
    }
    public float getDiaria() {
        return this.diaria;
    }
    public int[] getConsumo() {
        int[] itensConsumidos = Arrays.copyOf(this.consumo, this.nConsumo); //Copia apenas os itens consumidos
        return itensConsumidos; //retorna um vetor com os itens consumidos
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public void setCategoria(char categoria) {
        this.categoria = categoria;
    }
    public void setDiaria(float diaria) {
        this.diaria = diaria;
    }
    public void setConsumo(int[] consumo) {
        this.consumo = consumo;
    }
    public void adicionaConsumo(int codigoProduto) {
        if(this.nConsumo < this.consumo.length) { //verifica se há espaço no vetor
            this.consumo[this.nConsumo] = codigoProduto; //adiciona o código do produto no vetor
            this.nConsumo++; //incrementa o contador de itens consumidos
        } else {
            if (this.nConsumo == this.consumo.length) { //se o vetor estiver cheio
                consumo = Arrays.copyOf(this.consumo, nConsumo*2); //redimensiona o vetor consumo para o dobro do tamanho
                this.consumo[this.nConsumo] = codigoProduto; //adiciona o código do produto no vetor
                this.nConsumo++; //incrementa o contador de itens consumidos
            }
        }
    }

    public void limpaConsumo() {
        this.consumo = new int[5]; //reinicializa o vetor consumo com tamanho 5
        this.nConsumo = 0; //reinicializa o contador de itens consumidos
    }
    public void listaConsumo(Produto[] p){ //TODO: Receber objeto Produto em vez de Pousada
        for(Produto produto : p){
            for(int codProduto : this.getConsumo()){
                if(produto.getCodigo() == codProduto){
                    System.out.println(produto.toString());
                }

            }
        }
        /* for(int codProduto : p.getCodigo()){
            for(int cod : p.getCodigo()){
                if(cod == codProduto){
                    System.out.println(p.toString());
                }
            }
        } */
    }
    public void valorTotalConsumo(Produto[] p){
        float total = 0;
        for(int codProduto : this.getConsumo()){
            for(Produto prod : p){
                if(prod.getCodigo() == codProduto){
                    total += prod.getPreco();
                }
            }
        }
        System.out.printf("Valor total do consumo: R$ %.2f\n", total);
    }
    

    @Override
    public String toString() {
        return "Quarto " + this.numero + ":" + "[categoria=" + this.categoria + ", consumo=" + 
        Arrays.toString(this.consumo) + ", diaria=" + this.diaria + "]";
    }
}
