public class Reserva {
    private int diaInicio;
    private int diaFim;
    private String cliente;
    private Quarto quarto;
    private char status; //A - Ativa, C - Cancelada, F - Finalizada
    private int nReservas; //contador para o número de reservas

    Reserva(int diaInicio, int diaFim, String cliente, Quarto quarto, char status){
        this.diaInicio=0;
        this.diaFim=0;
        this.cliente="";
        this.quarto=new Quarto();
        this.status=' ';
        this.nReservas++;
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
    public void setnReservas(int nReservas) {
        this.nReservas = nReservas;
    }

    @Override
    public String toString() {
        return "Reserva [cliente=" + this.cliente + ", diaFim=" + this.diaFim + ", diaInicio=" + this.diaInicio
                + ", quarto=" + this.quarto + ", status=" + this.status + "]";
    }

}
