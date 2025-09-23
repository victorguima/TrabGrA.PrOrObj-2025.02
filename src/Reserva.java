public class Reserva {
    private int diaInicio;
    private int diaFim;
    private String cliente;
    private Quarto quarto;
    private char status; //A - Ativa, C - Cancelada, I - Check-In, O - Check-Out

    // Construtor com parâmetros - inicializa todos os atributos da reserva
    Reserva(int diaInicio, int diaFim, String cliente, Quarto quarto, char status){
        this.diaInicio = diaInicio;
        this.diaFim = diaFim;
        this.cliente = cliente;
        this.quarto = quarto;
        this.status = status;
    }

    // Construtor vazio - para criar reserva e definir atributos depois
    Reserva(){}

    // Retorna o dia de início da reserva
    public int getDiaInicio() {
        return this.diaInicio;
    }
    
    // Retorna o dia de fim da reserva
    public int getDiaFim() {
        return this.diaFim;
    }
    
    // Retorna o nome do cliente da reserva
    public String getCliente() {
        return this.cliente;
    }
    
    // Retorna o objeto Quarto associado à reserva
    public Quarto getQuarto() {
        return this.quarto;
    }   
    
    // Retorna o status da reserva (A/C/I/O)
    public char getStatus() {
        return this.status;
    }
    
    // Define o dia de início da reserva
    public void setDiaInicio(int diaInicio) {
        this.diaInicio = diaInicio;
    }
    
    // Define o dia de fim da reserva
    public void setDiaFim(int diaFim) {
        this.diaFim = diaFim;
    }
    
    // Define o nome do cliente da reserva
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
    // Define o quarto associado à reserva
    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }
    
    // Define o status da reserva (A - Ativa, C - Cancelada, I - Check-In, O - Check-Out)
    public void setStatus(char status) {
        this.status = status;
    }

    // Converte o objeto Reserva em texto para exibição - usado pelo println()
    @Override
    public String toString() {
        return "Reserva [cliente=" + this.cliente + ", diaFim=" + this.diaFim + ", diaInicio=" + this.diaInicio
                + ", quarto=" + this.quarto.getNumero() + ", status=" + this.status + "]";
    }
}