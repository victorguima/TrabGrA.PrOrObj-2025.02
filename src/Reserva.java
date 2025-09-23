public class Reserva {
    private int diaInicio;
    private int diaFim;
    private String cliente;
    private Quarto quarto;
    private char status; //A - Ativa, C - Cancelada, I - Realizou Check-in, O - Realizou Check-out

    Reserva(int diaInicio, int diaFim, String cliente, Quarto quarto, char status){
        this.diaInicio = diaInicio;
        this.diaFim = diaFim;
        this.cliente = cliente;
        this.quarto = quarto;
        this.status = status;
    }

    Reserva(){}

    public int getDiaInicio() {
        return this.diaInicio;
    }
    public int getDiaFim() {
        return this.diaFim;
    }
    public String getCliente() {
        return this.cliente;
    }
    public Quarto getQuarto() {
        return this.quarto;
    }   
    public char getStatus() {
        return this.status;
    }
    public void setDiaInicio(int diaInicio) {
        this.diaInicio = diaInicio;
    }
    public void setDiaFim(int diaFim) {
        this.diaFim = diaFim;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }
    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reserva do cliente " + this.cliente + ": Dias " + this.diaInicio + " a " + this.diaFim
                + ", quarto " + this.quarto.getNumero() + ", status " + this.status;
    }
}