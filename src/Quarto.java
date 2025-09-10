import java.util.Arrays;

public class Quarto {
    private int numero;
    private char categoria;
    private float diaria;
    private int consumo[];

    Quarto(){

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
        return this.consumo;
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

    @Override
    public String toString() {
        return "Quarto [categoria=" + this.categoria + ", consumo=" + Arrays.toString(this.consumo) + ", diaria=" + this.diaria
                + ", numero=" + this.numero + "]";
    }
}
