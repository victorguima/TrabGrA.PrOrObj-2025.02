import java.io.BufferedReader;
import java.util.Arrays;
import java.io.*;

public class Pousada {
    private String nome;
    private String contato;
    private Quarto[] quartos;
    private Reserva[] reservas;
    private Produto[] produtos;
    private int nQuartos;


    Pousada(){
        this.nome="NULO";
        this.contato="NULO";
        this.nQuartos=0;
        carregaDados();
    }

    public String getNome() {
        return this.nome;
    }
    public String getContato() {
        return this.contato;
    }
    public Quarto[] getQuartos() {
        return this.quartos;
    }
    public Reserva[] getReservas() {
        return this.reservas;
    }
    public Produto[] getProdutos() {
        return this.produtos;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setContato(String contato) {
        this.contato = contato;
    }
    public void setQuartos(Quarto[] quartos) {
        this.quartos = quartos;
    }
    public void setReservas(Reserva[] reservas) {
        this.reservas = reservas;
    }
    public void setProdutos(Produto[] produtos) {
        this.produtos = produtos;
    }
    public void carregaDados(){
        File arqPousada = new File("pousada.txt");
        try {
            if(arqPousada.exists()){
                BufferedReader brPousadaTxt = new BufferedReader(new FileReader(arqPousada));
                String[] dados = brPousadaTxt.readLine().split(";");
                setNome(dados[0]);
                setContato(dados[1]);
                this.nQuartos = brPousadaTxt.readLine().split(";").length;
                carregaQuartos();
                carregaReservas();
                carregaProdutos();
                System.out.println("Pousada carregada com sucesso.");
                brPousadaTxt.close();
            } else {
                System.out.println("Arquivo da pousada nÃ£o encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao acessar arquivo da pousada.");
            e.printStackTrace();
        }
    }
    private void carregaReservas(){
        File arqReservas = new File("reserva.txt");
        try{
            if(arqReservas.exists()){
                BufferedReader brReservasTxt = new BufferedReader(new FileReader(arqReservas));
                String linha=brReservasTxt.readLine();
                if(linha==null){
                    System.out.println("Arquivo de reservas vazio.");
                    brReservasTxt.close();
                    return;
                }
                String Reserva_atributos[]=linha.split(";");
                this.reservas = new Reserva[5];
                int nReservas=0;
                int j=0;
                for (int i = 0; i < this.reservas.length; i++) {
                    this.reservas[i]=new Reserva();
                    this.reservas[i].setDiaInicio(Integer.parseInt(Reserva_atributos[j++]));
                    this.reservas[i].setDiaFim(Integer.parseInt(Reserva_atributos[j++]));
                    this.reservas[i].setCliente(Reserva_atributos[j++]);
                    for(Quarto q: this.quartos){
                        if(q.getNumero() == Integer.parseInt(Reserva_atributos[j])){
                            this.reservas[i].setQuarto(q);
                            this.reservas[i].setStatus(Reserva_atributos[++j].charAt(0));
                            break;
                        }
                    }
                    nReservas++;
                    if(nReservas == this.reservas.length){
                        this.reservas = Arrays.copyOf(this.reservas, nReservas*2);
                    }
                    linha=brReservasTxt.readLine();
                    if(linha==null) break;
                    else Reserva_atributos=linha.split(";");
                    j=0;
                }
                this.reservas = Arrays.copyOf(this.reservas, nReservas);
                System.out.println("Reservas carregadas com sucesso.");
                brReservasTxt.close();
            }
            else{
                System.out.println("Arquivo de reservas nÃ£o encontrado.");
            }
        }catch(Exception e){   
            System.out.println("Erro ao acessar arquivo de reservas.");
            e.printStackTrace();
        }
    }
    private void carregaQuartos(){
        File arqQuartos = new File("quarto.txt");
        try{
            if(arqQuartos.exists()){
                BufferedReader brQuartosTxt = new BufferedReader(new FileReader(arqQuartos));
                String linha=brQuartosTxt.readLine();
                if(linha==null){
                    System.out.println("Arquivo de quartos vazio.");
                    brQuartosTxt.close();
                    return;
                }
                String Quarto_atributos[]=linha.split(";");
                this.quartos = new Quarto[this.nQuartos];
                int j=0;
                for (int i = 0; i < this.nQuartos; i++) {
                    this.quartos[i]=new Quarto();
                    this.quartos[i].setNumero(Integer.parseInt(Quarto_atributos[j++]));
                    this.quartos[i].setCategoria(Quarto_atributos[j++].charAt(0));
                    this.quartos[i].setDiaria(Float.parseFloat(Quarto_atributos[j++]));
                    linha=brQuartosTxt.readLine();
                    if(linha==null) break;
                    else Quarto_atributos=linha.split(";");
                    j=0;
                }
                System.out.println("Quartos carregados com sucesso.");
                brQuartosTxt.close();
            }
            else{
                System.out.println("Arquivo de quartos nÃ£o encontrado.");
            }
        }catch(Exception e){   
            System.out.println("Erro ao acessar arquivo de quartos.");
            e.printStackTrace();
        }
    }
    private void carregaProdutos(){
        File arqProdutos = new File("produto.txt");
        try{
            if(arqProdutos.exists()){
                BufferedReader brProdutosTxt = new BufferedReader(new FileReader(arqProdutos));
                String linha=brProdutosTxt.readLine();
                if(linha==null){
                    System.out.println("Arquivo de produtos vazio.");
                    brProdutosTxt.close();
                    return;
                }
                String Produto_atributos[]=linha.split(";");
                this.produtos = new Produto[5];
                int nProdutos=0;
                int j=0;
                for (int i = 0; i < this.produtos.length; i++) {
                    this.produtos[i]=new Produto();
                    this.produtos[i].setCodigo(Integer.parseInt(Produto_atributos[j++]));
                    this.produtos[i].setNome(Produto_atributos[j++]);
                    this.produtos[i].setPreco(Float.parseFloat(Produto_atributos[j++]));
                    nProdutos++;
                    if(nProdutos == this.produtos.length){
                        this.produtos = Arrays.copyOf(this.produtos, nProdutos*2);
                    }
                    linha=brProdutosTxt.readLine();
                    if(linha==null) break;
                    else Produto_atributos=linha.split(";");
                    j=0;
                }
                this.produtos = Arrays.copyOf(this.produtos, nProdutos);
                System.out.println("Produtos carregados com sucesso.");
                brProdutosTxt.close();
            }
            else{
                System.out.println("Arquivo de produtos nÃ£o encontrado.");
            }
        }catch(Exception e){   
            System.out.println("Erro ao acessar arquivo de produtos.");
            e.printStackTrace();
        }
    }
    
    public boolean consultaDisponibilidade(int data, int numeroQuarto) {
        Quarto quartoEncontrado = null;
        for (Quarto q : this.quartos) {
            if (q.getNumero() == numeroQuarto) {
                quartoEncontrado = q;
                break;
            }
        }
        
        if (quartoEncontrado == null) {
            System.out.println("Quarto não encontrado.");
            return false;
        }
        
        for (Reserva r : this.reservas) {
            if (r.getQuarto().getNumero() == numeroQuarto && 
                (r.getStatus() == 'A' || r.getStatus() == 'I') &&
                data >= r.getDiaInicio() && data <= r.getDiaFim()) {
                System.out.println("Quarto ocupado nesta data.");
                return false;
            }
        }
        
        System.out.println("Quarto " + quartoEncontrado.getNumero() + 
                           " - Categoria: " + quartoEncontrado.getCategoria() + 
                           " - Diária: R$" + quartoEncontrado.getDiaria());
        return true;
    }

    public void cancelaReserva(String cliente) {
        boolean reservaEncontrada = false;
        for (Reserva r : this.reservas) {
            if (r.getCliente().equals(cliente) && r.getStatus() == 'A') {
                r.setStatus('C');
                System.out.println("Reserva do cliente " + cliente + " cancelada com sucesso.");
                reservaEncontrada = true;
                break;
            }
        }
        if (!reservaEncontrada) {
            System.out.println("Nenhuma reserva ativa encontrada para o cliente " + cliente + ".");
        }
    }


}